// CheckStyle: start generated
package fr.cea.nabla.interpreter.values;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.values.BoxMeshCallResultNode;
import org.graalvm.polyglot.Value;

@GeneratedBy(BoxMeshCallResultNode.class)
public final class BoxMeshCallResultNodeGen extends BoxMeshCallResultNode {

    @CompilationFinal private int state_;

    private BoxMeshCallResultNodeGen() {
    }

    @Override
    public Object execute(Object arg0Value) {
        int state = state_;
        if (state != 0 /* is-active doInt(Value) || doHostObject(Value) || doNativeArray(Value) */ && arg0Value instanceof Value) {
            Value arg0Value_ = (Value) arg0Value;
            if ((state & 0b1) != 0 /* is-active doInt(Value) */) {
                if ((arg0Value_.fitsInInt())) {
                    return doInt(arg0Value_);
                }
            }
            if ((state & 0b10) != 0 /* is-active doHostObject(Value) */) {
                if ((arg0Value_.isHostObject())) {
                    return doHostObject(arg0Value_);
                }
            }
            if ((state & 0b100) != 0 /* is-active doNativeArray(Value) */) {
                if ((arg0Value_.isNativePointer()) && (arg0Value_.hasArrayElements())) {
                    return doNativeArray(arg0Value_);
                }
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(arg0Value);
    }

    private Object executeAndSpecialize(Object arg0Value) {
        int state = state_;
        if (arg0Value instanceof Value) {
            Value arg0Value_ = (Value) arg0Value;
            if ((arg0Value_.fitsInInt())) {
                this.state_ = state = state | 0b1 /* add-active doInt(Value) */;
                return doInt(arg0Value_);
            }
            if ((arg0Value_.isHostObject())) {
                this.state_ = state = state | 0b10 /* add-active doHostObject(Value) */;
                return doHostObject(arg0Value_);
            }
            if ((arg0Value_.isNativePointer()) && (arg0Value_.hasArrayElements())) {
                this.state_ = state = state | 0b100 /* add-active doNativeArray(Value) */;
                return doNativeArray(arg0Value_);
            }
        }
        throw new UnsupportedSpecializationException(this, new Node[] {null}, arg0Value);
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

    public static BoxMeshCallResultNode create() {
        return new BoxMeshCallResultNodeGen();
    }

}
