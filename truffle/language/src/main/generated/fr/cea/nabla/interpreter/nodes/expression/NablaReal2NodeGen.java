// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaReal2Node;

@GeneratedBy(NablaReal2Node.class)
public final class NablaReal2NodeGen extends NablaReal2Node {

    private NablaReal2NodeGen(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
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

    public static NablaReal2Node create(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
        return new NablaReal2NodeGen(values, dimensions);
    }

}
