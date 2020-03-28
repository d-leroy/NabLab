// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaInt3ConstantNode;

@GeneratedBy(NablaInt3ConstantNode.class)
public final class NablaInt3ConstantNodeGen extends NablaInt3ConstantNode {

    private NablaInt3ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        super(value, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV3Int(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaInt3ConstantNode create(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        return new NablaInt3ConstantNodeGen(value, dimensions);
    }

}
