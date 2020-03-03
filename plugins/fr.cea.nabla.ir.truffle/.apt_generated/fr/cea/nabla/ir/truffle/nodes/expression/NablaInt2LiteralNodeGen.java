// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaInt2LiteralNode;

@GeneratedBy(NablaInt2LiteralNode.class)
public final class NablaInt2LiteralNodeGen extends NablaInt2LiteralNode {

    private NablaInt2LiteralNodeGen(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
        super(values, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV2Int(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaInt2LiteralNode create(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
        return new NablaInt2LiteralNodeGen(values, dimensions);
    }

}
