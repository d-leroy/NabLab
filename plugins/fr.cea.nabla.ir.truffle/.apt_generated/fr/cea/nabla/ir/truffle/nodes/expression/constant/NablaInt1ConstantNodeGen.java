// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaInt1ConstantNode;

@GeneratedBy(NablaInt1ConstantNode.class)
public final class NablaInt1ConstantNodeGen extends NablaInt1ConstantNode {

    private NablaInt1ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode size) {
        super(value, size);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV1Int(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaInt1ConstantNode create(NablaExpressionNode value, NablaExpressionNode size) {
        return new NablaInt1ConstantNodeGen(value, size);
    }

}
