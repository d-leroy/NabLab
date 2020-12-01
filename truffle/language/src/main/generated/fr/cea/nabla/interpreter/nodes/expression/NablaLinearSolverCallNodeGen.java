// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.values.NV1RealJava;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.NablaValue;
import fr.cea.nabla.javalib.types.LinearAlgebraFunctions;

@GeneratedBy(NablaLinearSolverCallNode.class)
public final class NablaLinearSolverCallNodeGen extends NablaLinearSolverCallNode {

    @Child private NablaExpressionNode matrix_;
    @Child private NablaExpressionNode vector_;
    @CompilationFinal private int state_;

    private NablaLinearSolverCallNodeGen(LinearAlgebraFunctions solver, NablaExpressionNode matrix, NablaExpressionNode vector) {
        super(solver);
        this.matrix_ = matrix;
        this.vector_ = vector;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object matrixValue_ = this.matrix_.executeGeneric(frameValue);
        Object vectorValue_ = this.vector_.executeGeneric(frameValue);
        if (state != 0 /* is-active solve(VirtualFrame, NV2Real, NV1RealJava) */ && matrixValue_ instanceof NV2Real) {
            NV2Real matrixValue__ = (NV2Real) matrixValue_;
            if (vectorValue_ instanceof NV1RealJava) {
                NV1RealJava vectorValue__ = (NV1RealJava) vectorValue_;
                return solve(frameValue, matrixValue__, vectorValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, matrixValue_, vectorValue_);
    }

    private NablaValue executeAndSpecialize(VirtualFrame frameValue, Object matrixValue, Object vectorValue) {
        int state = state_;
        if (matrixValue instanceof NV2Real) {
            NV2Real matrixValue_ = (NV2Real) matrixValue;
            if (vectorValue instanceof NV1RealJava) {
                NV1RealJava vectorValue_ = (NV1RealJava) vectorValue;
                this.state_ = state = state | 0b1 /* add-active solve(VirtualFrame, NV2Real, NV1RealJava) */;
                return solve(frameValue, matrixValue_, vectorValue_);
            }
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.matrix_, this.vector_}, matrixValue, vectorValue);
    }

    @Override
    public NodeCost getCost() {
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else {
            return NodeCost.MONOMORPHIC;
        }
    }

    public static NablaLinearSolverCallNode create(LinearAlgebraFunctions solver, NablaExpressionNode matrix, NablaExpressionNode vector) {
        return new NablaLinearSolverCallNodeGen(solver, matrix, vector);
    }

}
