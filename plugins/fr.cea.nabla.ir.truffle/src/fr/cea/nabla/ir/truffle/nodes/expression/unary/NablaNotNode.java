package fr.cea.nabla.ir.truffle.nodes.expression.unary;

import com.oracle.truffle.api.dsl.Fallback;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

import fr.cea.nabla.ir.truffle.NablaException;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Bool;

@NodeChild("valueNode")
@NodeInfo(shortName = "!")
public abstract class NablaNotNode extends NablaExpressionNode {

	@Specialization
    protected NV0Bool not(NV0Bool value) {
        return new NV0Bool(!value.isData());
    }

    @Fallback
    protected Object typeError(Object value) {
        throw NablaException.typeError(this, value);
    }
}
