// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaBool2Node;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;

@GeneratedBy(NablaBool2Node.class)
public final class NablaBool2NodeGen extends NablaBool2Node {

    private NablaBool2NodeGen(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
        super(values, dimensions);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV2Bool(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaBool2Node create(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
        return new NablaBool2NodeGen(values, dimensions);
    }

}
