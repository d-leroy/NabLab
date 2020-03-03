package fr.cea.nabla.ir.truffle;

import com.oracle.truffle.api.dsl.TypeSystem;

import fr.cea.nabla.ir.truffle.values.NV0Bool;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;
import fr.cea.nabla.ir.truffle.values.NV3Int;
import fr.cea.nabla.ir.truffle.values.NV3Real;
import fr.cea.nabla.ir.truffle.values.NV4Int;
import fr.cea.nabla.ir.truffle.values.NV4Real;
import fr.cea.nabla.ir.truffle.values.NablaOutput;


@TypeSystem({NV0Bool.class, NV0Int.class, NV0Real.class, NV1Int.class,
	NV1Real.class, NV2Int.class, NV2Real.class, NV3Int.class,
	NV3Real.class, NV4Int.class, NV4Real.class, NablaOutput.class})
public abstract class NablaTypes {}