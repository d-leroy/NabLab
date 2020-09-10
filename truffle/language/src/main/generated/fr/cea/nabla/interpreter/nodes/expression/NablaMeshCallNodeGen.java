// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaMeshCallNode;
import java.util.concurrent.locks.Lock;
import org.graalvm.polyglot.Value;

@GeneratedBy(NablaMeshCallNode.class)
public final class NablaMeshCallNodeGen extends NablaMeshCallNode {

    @CompilationFinal private int state_;
    @CompilationFinal private Value call0_mesh_;
    @CompilationFinal private Value call0_value_;
    @CompilationFinal private Value call1_value_;

    private NablaMeshCallNodeGen(String connectivityName, NablaExpressionNode[] argumentNodes) {
        super(connectivityName, argumentNodes);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        if ((state & 0b1) != 0 /* is-active call(VirtualFrame, Value, Value) */) {
            assert (!(isStatic));
            return call(frameValue, this.call0_mesh_, this.call0_value_);
        }
        if ((state & 0b10) != 0 /* is-active call(VirtualFrame, Value) */) {
            assert (isStatic);
            return call(frameValue, this.call1_value_);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        try {
            if ((!(isStatic))) {
                this.call0_mesh_ = (getMesh());
                this.call0_value_ = (this.call0_mesh_.getMember(connectivityName));
                this.state_ = state = state | 0b1 /* add-active call(VirtualFrame, Value, Value) */;
                lock.unlock();
                hasLock = false;
                return call(frameValue, this.call0_mesh_, this.call0_value_);
            }
            if ((isStatic)) {
                this.call1_value_ = (getMaxNbElems(connectivityName));
                this.state_ = state = state | 0b10 /* add-active call(VirtualFrame, Value) */;
                lock.unlock();
                hasLock = false;
                return call(frameValue, this.call1_value_);
            }
            throw new UnsupportedSpecializationException(this, new Node[] {});
        } finally {
            if (hasLock) {
                lock.unlock();
            }
        }
    }

    @Override
    public NodeCost getCost() {
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
            return NodeCost.MONOMORPHIC;
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaMeshCallNode create(String connectivityName, NablaExpressionNode[] argumentNodes) {
        return new NablaMeshCallNodeGen(connectivityName, argumentNodes);
    }

}
