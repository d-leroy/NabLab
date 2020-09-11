// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaInt2Node;

@GeneratedBy(NablaInt2Node.class)
public final class NablaInt2NodeGen extends NablaInt2Node {

    private NablaInt2NodeGen(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
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

    public static NablaInt2Node create(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
        return new NablaInt2NodeGen(values, dimensions);
    }

}
