package fr.cea.nabla.interpreter.tools;

import java.util.HashMap;
import java.util.Map;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.UnmatchedListener;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExecutableNode;

@NodeChild(value = "value", type = ExecutableNode.class)
public abstract class StreamAppenderNode extends ActionNode {

	private final EPRuntime epRuntime;
	private final String eventType;

	public StreamAppenderNode(EPRuntime epRuntime, String eventType) {
		this.epRuntime = epRuntime;
		this.eventType = eventType;
		epRuntime.getEventService().setUnmatchedListener(new UnmatchedListener() {
			@Override
			public void update(EventBean arg0) {
				System.out.println("received: " + arg0.getEventType().getName() + " --> " + arg0.get("value"));
			}
		});
	}

	@Specialization
	protected void append(VirtualFrame frame, Object value) {
		sendEvent(value);
		frame.getFrameDescriptor().getSlots().forEach(s -> {
			switch (frame.getFrameDescriptor().getFrameSlotKind(s)) {
			case Object:
				try {
					System.out.println(frame.getObject(s).toString());
				} catch (FrameSlotTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setObject(s, new Object());
				break;
			default:
				break;
			}
		});
	}
	
	@TruffleBoundary
	private void sendEvent(Object value) {
		final Map<String, Object> event = new HashMap<>(1);
		event.put("value", value);
		epRuntime.getEventService().sendEventMap(event, eventType);
	}

}
