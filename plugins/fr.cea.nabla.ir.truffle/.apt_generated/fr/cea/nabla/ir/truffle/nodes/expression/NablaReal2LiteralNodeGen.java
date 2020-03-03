// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReal2LiteralNode;

@GeneratedBy(NablaReal2LiteralNode.class)
public final class NablaReal2LiteralNodeGen extends NablaReal2LiteralNode {

    private NablaReal2LiteralNodeGen(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
        super(values, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV2Real(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaReal2LiteralNode create(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
        return new NablaReal2LiteralNodeGen(values, dimensions);
    }

}
