// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.constant.NablaBool2ConstantNode;

@GeneratedBy(NablaBool2ConstantNode.class)
public final class NablaBool2ConstantNodeGen extends NablaBool2ConstantNode {

    private NablaBool2ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        super(value, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV2Bool(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaBool2ConstantNode create(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
        return new NablaBool2ConstantNodeGen(value, dimensions);
    }

}
