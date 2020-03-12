// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaReal1ConstantNode;

@GeneratedBy(NablaReal1ConstantNode.class)
public final class NablaReal1ConstantNodeGen extends NablaReal1ConstantNode {

    private NablaReal1ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode size) {
        super(value, size);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV1Real(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaReal1ConstantNode create(NablaExpressionNode value, NablaExpressionNode size) {
        return new NablaReal1ConstantNodeGen(value, size);
    }

}
