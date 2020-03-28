// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaBool4ConstantNode;

@GeneratedBy(NablaBool4ConstantNode.class)
public final class NablaBool4ConstantNodeGen extends NablaBool4ConstantNode {

    private NablaBool4ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        super(value, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV4Bool(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaBool4ConstantNode create(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        return new NablaBool4ConstantNodeGen(value, dimensions);
    }

}
