// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.espertech.esper.runtime.client.EPRuntime;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExecutableNode;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.tools.StreamAppenderNode;

@GeneratedBy(StreamAppenderNode.class)
public final class StreamAppenderNodeGen extends StreamAppenderNode {

    @Child private ExecutableNode value_;

    private StreamAppenderNodeGen(EPRuntime epRuntime, String eventType, ExecutableNode value) {
        super(epRuntime, eventType);
        this.value_ = value;
    }

    @Override
    public void execute(VirtualFrame frameValue) {
        Object valueValue_ = this.value_.execute(frameValue);
        append(frameValue, valueValue_);
        return;
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static StreamAppenderNode create(EPRuntime epRuntime, String eventType, ExecutableNode value) {
        return new StreamAppenderNodeGen(epRuntime, eventType, value);
    }

}
