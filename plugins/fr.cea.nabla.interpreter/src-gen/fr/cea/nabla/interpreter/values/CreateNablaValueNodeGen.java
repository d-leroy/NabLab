// CheckStyle: start generated
package fr.cea.nabla.interpreter.values;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.values.CreateNablaValueNode;
import fr.cea.nabla.interpreter.values.NablaValue;

@GeneratedBy(CreateNablaValueNode.class)
public final class CreateNablaValueNodeGen extends CreateNablaValueNode {

    @CompilationFinal private int state_;

    private CreateNablaValueNodeGen() {
    }

    @Override
    public NablaValue execute(Object arg0Value) {
        int state = state_;
        if ((state & 0b1) != 0 /* is-active createNablaValue(Boolean) */ && arg0Value instanceof Boolean) {
            Boolean arg0Value_ = (Boolean) arg0Value;
            return createNablaValue(arg0Value_);
        }
        if ((state & 0b10) != 0 /* is-active createNablaValue(boolean[]) */ && arg0Value instanceof boolean[]) {
            boolean[] arg0Value_ = (boolean[]) arg0Value;
            return createNablaValue(arg0Value_);
        }
        if ((state & 0b100) != 0 /* is-active createNablaValue(boolean[][]) */ && arg0Value instanceof boolean[][]) {
            boolean[][] arg0Value_ = (boolean[][]) arg0Value;
            return createNablaValue(arg0Value_);
        }
        if ((state & 0b1000) != 0 /* is-active createNablaValue(Integer) */ && arg0Value instanceof Integer) {
            Integer arg0Value_ = (Integer) arg0Value;
            return createNablaValue(arg0Value_);
        }
        if ((state & 0b10000) != 0 /* is-active createNablaValue(int[]) */ && arg0Value instanceof int[]) {
            int[] arg0Value_ = (int[]) arg0Value;
            return createNablaValue(arg0Value_);
        }
        if ((state & 0b100000) != 0 /* is-active createNablaValue(int[][]) */ && arg0Value instanceof int[][]) {
            int[][] arg0Value_ = (int[][]) arg0Value;
            return createNablaValue(arg0Value_);
        }
        if ((state & 0b1000000) != 0 /* is-active createNablaValue(Double) */ && arg0Value instanceof Double) {
            Double arg0Value_ = (Double) arg0Value;
            return createNablaValue(arg0Value_);
        }
        if ((state & 0b10000000) != 0 /* is-active createNablaValue(double[]) */ && arg0Value instanceof double[]) {
            double[] arg0Value_ = (double[]) arg0Value;
            return createNablaValue(arg0Value_);
        }
        if ((state & 0b100000000) != 0 /* is-active createNablaValue(double[][]) */ && arg0Value instanceof double[][]) {
            double[][] arg0Value_ = (double[][]) arg0Value;
            return createNablaValue(arg0Value_);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(arg0Value);
    }

    private NablaValue executeAndSpecialize(Object arg0Value) {
        int state = state_;
        if (arg0Value instanceof Boolean) {
            Boolean arg0Value_ = (Boolean) arg0Value;
            this.state_ = state = state | 0b1 /* add-active createNablaValue(Boolean) */;
            return createNablaValue(arg0Value_);
        }
        if (arg0Value instanceof boolean[]) {
            boolean[] arg0Value_ = (boolean[]) arg0Value;
            this.state_ = state = state | 0b10 /* add-active createNablaValue(boolean[]) */;
            return createNablaValue(arg0Value_);
        }
        if (arg0Value instanceof boolean[][]) {
            boolean[][] arg0Value_ = (boolean[][]) arg0Value;
            this.state_ = state = state | 0b100 /* add-active createNablaValue(boolean[][]) */;
            return createNablaValue(arg0Value_);
        }
        if (arg0Value instanceof Integer) {
            Integer arg0Value_ = (Integer) arg0Value;
            this.state_ = state = state | 0b1000 /* add-active createNablaValue(Integer) */;
            return createNablaValue(arg0Value_);
        }
        if (arg0Value instanceof int[]) {
            int[] arg0Value_ = (int[]) arg0Value;
            this.state_ = state = state | 0b10000 /* add-active createNablaValue(int[]) */;
            return createNablaValue(arg0Value_);
        }
        if (arg0Value instanceof int[][]) {
            int[][] arg0Value_ = (int[][]) arg0Value;
            this.state_ = state = state | 0b100000 /* add-active createNablaValue(int[][]) */;
            return createNablaValue(arg0Value_);
        }
        if (arg0Value instanceof Double) {
            Double arg0Value_ = (Double) arg0Value;
            this.state_ = state = state | 0b1000000 /* add-active createNablaValue(Double) */;
            return createNablaValue(arg0Value_);
        }
        if (arg0Value instanceof double[]) {
            double[] arg0Value_ = (double[]) arg0Value;
            this.state_ = state = state | 0b10000000 /* add-active createNablaValue(double[]) */;
            return createNablaValue(arg0Value_);
        }
        if (arg0Value instanceof double[][]) {
            double[][] arg0Value_ = (double[][]) arg0Value;
            this.state_ = state = state | 0b100000000 /* add-active createNablaValue(double[][]) */;
            return createNablaValue(arg0Value_);
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

    public static CreateNablaValueNode create() {
        return new CreateNablaValueNodeGen();
    }

}
