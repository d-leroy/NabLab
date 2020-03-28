// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaBool1Node;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;

@GeneratedBy(NablaBool1Node.class)
public final class NablaBool1NodeGen extends NablaBool1Node {

    private NablaBool1NodeGen(NablaExpressionNode[] values) {
        super(values);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return executeNV1Bool(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaBool1Node create(NablaExpressionNode[] values) {
        return new NablaBool1NodeGen(values);
    }

}
