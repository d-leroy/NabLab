// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaBool1ConstantNode;

@GeneratedBy(NablaBool1ConstantNode.class)
public final class NablaBool1ConstantNodeGen extends NablaBool1ConstantNode {

    private NablaBool1ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode size) {
        super(value, size);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV1Bool(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaBool1ConstantNode create(NablaExpressionNode value, NablaExpressionNode size) {
        return new NablaBool1ConstantNodeGen(value, size);
    }

}
