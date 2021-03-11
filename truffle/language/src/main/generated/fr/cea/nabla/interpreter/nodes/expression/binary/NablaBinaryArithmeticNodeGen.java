// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.binary;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.EncapsulatingNodeReference;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.profiles.LoopConditionProfile;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import fr.cea.nabla.interpreter.values.NV1RealJava;
import fr.cea.nabla.interpreter.values.NV1RealLibrary;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaBinaryArithmeticNode.class)
@SuppressWarnings("unused")
public final class NablaBinaryArithmeticNodeGen extends NablaBinaryArithmeticNode {

    private static final LibraryFactory<NV1IntLibrary> N_V1_INT_LIBRARY_ = LibraryFactory.resolve(NV1IntLibrary.class);
    private static final LibraryFactory<NV1RealLibrary> N_V1_REAL_LIBRARY_ = LibraryFactory.resolve(NV1RealLibrary.class);

    @Child private NablaExpressionNode leftNode_;
    @Child private NablaExpressionNode rightNode_;
    @CompilationFinal private volatile int state_0_;
    @CompilationFinal private volatile int state_1_;
    @CompilationFinal private volatile int exclude_;
    @Child private Binary2Data binary2_cache;
    @CompilationFinal private LoopConditionProfile binary3_loopProfile_;
    @Child private Binary4Data binary4_cache;
    @CompilationFinal private LoopConditionProfile binary5_loopProfile_;
    @CompilationFinal private LoopConditionProfile binary6_outerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary6_innerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary7_outerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary7_innerLoopProfile_;
    @Child private Binary10Data binary10_cache;
    @CompilationFinal private LoopConditionProfile binary11_loopProfile_;
    @Child private Binary12Data binary12_cache;
    @CompilationFinal private LoopConditionProfile binary13_loopProfile_;
    @CompilationFinal private LoopConditionProfile binary14_outerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary14_innerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary15_outerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary15_innerLoopProfile_;
    @Child private Binary16Data binary16_cache;
    @CompilationFinal private LoopConditionProfile binary17_loopProfile_;
    @Child private Binary18Data binary18_cache;
    @CompilationFinal private LoopConditionProfile binary19_loopProfile_;
    @Child private Binary20Data binary20_cache;
    @CompilationFinal private LoopConditionProfile binary21_loopProfile_;
    @Child private Binary22Data binary22_cache;
    @CompilationFinal private LoopConditionProfile binary23_loopProfile_;
    @CompilationFinal private LoopConditionProfile binary24_loopProfile_;
    @CompilationFinal private LoopConditionProfile binary25_loopProfile_;
    @Child private Binary26Data binary26_cache;
    @CompilationFinal private LoopConditionProfile binary27_loopProfile_;
    @Child private NV1NV1CachedData nV1NV1Cached_cache;
    @Child private Binary28Data binary28_cache;
    @CompilationFinal private LoopConditionProfile binary29_loopProfile_;
    @CompilationFinal private LoopConditionProfile binary30_outerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary30_innerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary31_outerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary31_innerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary32_outerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary32_innerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary33_outerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary33_innerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary34_outerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary34_innerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary35_outerLoopProfile_;
    @CompilationFinal private LoopConditionProfile binary35_innerLoopProfile_;

    private NablaBinaryArithmeticNodeGen(ArithmeticOperator operator, NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        super(operator);
        this.leftNode_ = leftNode;
        this.rightNode_ = rightNode;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        int state_1 = state_1_;
        Object leftNodeValue_ = this.leftNode_.executeGeneric(frameValue);
        Object rightNodeValue_ = this.rightNode_.executeGeneric(frameValue);
        if (((state_0 & 0b11111111) != 0 /* is-state_0 doBinary(NV0Int, NV0Int) || doBinary(NV0Int, NV0Real) || doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) || doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) || doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) || doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) || doBinary(NV0Int, NV2Int, LoopConditionProfile, LoopConditionProfile) || doBinary(NV0Int, NV2Real, LoopConditionProfile, LoopConditionProfile) */) && leftNodeValue_ instanceof NV0Int) {
            NV0Int leftNodeValue__ = (NV0Int) leftNodeValue_;
            if (((state_0 & 0b1) != 0 /* is-state_0 doBinary(NV0Int, NV0Int) */) && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__);
            }
            if (((state_0 & 0b10) != 0 /* is-state_0 doBinary(NV0Int, NV0Real) */) && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__);
            }
            if (((state_0 & 0b111100) != 0 /* is-state_0 doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) || doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) || doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) || doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) */)) {
                if (((state_0 & 0b100) != 0 /* is-state_0 doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) */)) {
                    Binary2Data s3_ = this.binary2_cache;
                    while (s3_ != null) {
                        if ((s3_.arrays_.accepts(rightNodeValue_)) && (s3_.arrays_.isArray(rightNodeValue_))) {
                            return doBinary(leftNodeValue__, rightNodeValue_, s3_.arrays_, s3_.loopProfile_);
                        }
                        s3_ = s3_.next_;
                    }
                }
                if (((state_0 & 0b1000) != 0 /* is-state_0 doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) */)) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary binary3_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((binary3_arrays__.isArray(rightNodeValue_))) {
                                return this.binary3Boundary(state_0, state_1, leftNodeValue__, rightNodeValue_);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
                if (((state_0 & 0b10000) != 0 /* is-state_0 doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) */)) {
                    Binary4Data s5_ = this.binary4_cache;
                    while (s5_ != null) {
                        if ((s5_.arrays_.accepts(rightNodeValue_)) && (s5_.arrays_.isArray(rightNodeValue_))) {
                            return doBinary(leftNodeValue__, rightNodeValue_, s5_.arrays_, s5_.loopProfile_);
                        }
                        s5_ = s5_.next_;
                    }
                }
                if (((state_0 & 0b100000) != 0 /* is-state_0 doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) */)) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1RealLibrary binary5_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                            if ((binary5_arrays__.isArray(rightNodeValue_))) {
                                return this.binary5Boundary(state_0, state_1, leftNodeValue__, rightNodeValue_);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((state_0 & 0b1000000) != 0 /* is-state_0 doBinary(NV0Int, NV2Int, LoopConditionProfile, LoopConditionProfile) */) && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary6_outerLoopProfile_, this.binary6_innerLoopProfile_);
            }
            if (((state_0 & 0b10000000) != 0 /* is-state_0 doBinary(NV0Int, NV2Real, LoopConditionProfile, LoopConditionProfile) */) && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary7_outerLoopProfile_, this.binary7_innerLoopProfile_);
            }
        }
        if (((state_0 & 0b1111111100000000) != 0 /* is-state_0 doBinary(NV0Real, NV0Int) || doBinary(NV0Real, NV0Real) || doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) || doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) || doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) || doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) || doBinary(NV0Real, NV2Int, LoopConditionProfile, LoopConditionProfile) || doBinary(NV0Real, NV2Real, LoopConditionProfile, LoopConditionProfile) */) && leftNodeValue_ instanceof NV0Real) {
            NV0Real leftNodeValue__ = (NV0Real) leftNodeValue_;
            if (((state_0 & 0b100000000) != 0 /* is-state_0 doBinary(NV0Real, NV0Int) */) && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__);
            }
            if (((state_0 & 0b1000000000) != 0 /* is-state_0 doBinary(NV0Real, NV0Real) */) && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__);
            }
            if (((state_0 & 0b11110000000000) != 0 /* is-state_0 doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) || doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) || doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) || doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) */)) {
                if (((state_0 & 0b10000000000) != 0 /* is-state_0 doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) */)) {
                    Binary10Data s11_ = this.binary10_cache;
                    while (s11_ != null) {
                        if ((s11_.arrays_.accepts(rightNodeValue_)) && (s11_.arrays_.isArray(rightNodeValue_))) {
                            return doBinary(leftNodeValue__, rightNodeValue_, s11_.arrays_, s11_.loopProfile_);
                        }
                        s11_ = s11_.next_;
                    }
                }
                if (((state_0 & 0b100000000000) != 0 /* is-state_0 doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) */)) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary binary11_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((binary11_arrays__.isArray(rightNodeValue_))) {
                                return this.binary11Boundary(state_0, state_1, leftNodeValue__, rightNodeValue_);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
                if (((state_0 & 0b1000000000000) != 0 /* is-state_0 doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) */)) {
                    Binary12Data s13_ = this.binary12_cache;
                    while (s13_ != null) {
                        if ((s13_.arrays_.accepts(rightNodeValue_)) && (s13_.arrays_.isArray(rightNodeValue_))) {
                            return doBinary(leftNodeValue__, rightNodeValue_, s13_.arrays_, s13_.loopProfile_);
                        }
                        s13_ = s13_.next_;
                    }
                }
                if (((state_0 & 0b10000000000000) != 0 /* is-state_0 doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) */)) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1RealLibrary binary13_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                            if ((binary13_arrays__.isArray(rightNodeValue_))) {
                                return this.binary13Boundary(state_0, state_1, leftNodeValue__, rightNodeValue_);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((state_0 & 0b100000000000000) != 0 /* is-state_0 doBinary(NV0Real, NV2Int, LoopConditionProfile, LoopConditionProfile) */) && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary14_outerLoopProfile_, this.binary14_innerLoopProfile_);
            }
            if (((state_0 & 0b1000000000000000) != 0 /* is-state_0 doBinary(NV0Real, NV2Real, LoopConditionProfile, LoopConditionProfile) */) && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary15_outerLoopProfile_, this.binary15_innerLoopProfile_);
            }
        }
        if (((state_0 & 0xff0000) != 0 /* is-state_0 doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) || doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) || doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) || doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) || doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) || doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) || doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) || doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) */)) {
            if (((state_0 & 0x30000) != 0 /* is-state_0 doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) || doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) */) && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                if (((state_0 & 0x10000) != 0 /* is-state_0 doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) */)) {
                    Binary16Data s17_ = this.binary16_cache;
                    while (s17_ != null) {
                        if ((s17_.arrays_.accepts(leftNodeValue_)) && (s17_.arrays_.isArray(leftNodeValue_))) {
                            return doBinary(leftNodeValue_, rightNodeValue__, s17_.arrays_, s17_.loopProfile_);
                        }
                        s17_ = s17_.next_;
                    }
                }
                if (((state_0 & 0x20000) != 0 /* is-state_0 doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) */)) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary binary17_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((binary17_arrays__.isArray(leftNodeValue_))) {
                                return this.binary17Boundary(state_0, state_1, leftNodeValue_, rightNodeValue__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((state_0 & 0xc0000) != 0 /* is-state_0 doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) || doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) */) && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                if (((state_0 & 0x40000) != 0 /* is-state_0 doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) */)) {
                    Binary18Data s19_ = this.binary18_cache;
                    while (s19_ != null) {
                        if ((s19_.arrays_.accepts(leftNodeValue_)) && (s19_.arrays_.isArray(leftNodeValue_))) {
                            return doBinary(leftNodeValue_, rightNodeValue__, s19_.arrays_, s19_.loopProfile_);
                        }
                        s19_ = s19_.next_;
                    }
                }
                if (((state_0 & 0x80000) != 0 /* is-state_0 doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) */)) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary binary19_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((binary19_arrays__.isArray(leftNodeValue_))) {
                                return this.binary19Boundary(state_0, state_1, leftNodeValue_, rightNodeValue__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((state_0 & 0xf00000) != 0 /* is-state_0 doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) || doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) || doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) || doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) */)) {
                if (((state_0 & 0x100000) != 0 /* is-state_0 doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) */)) {
                    Binary20Data s21_ = this.binary20_cache;
                    while (s21_ != null) {
                        if ((s21_.arraysLeft_.accepts(leftNodeValue_)) && (s21_.arraysRight_.accepts(rightNodeValue_)) && (s21_.arraysLeft_.isArray(leftNodeValue_)) && (s21_.arraysRight_.isArray(rightNodeValue_))) {
                            return doBinary(leftNodeValue_, rightNodeValue_, s21_.arraysLeft_, s21_.arraysRight_, s21_.loopProfile_);
                        }
                        s21_ = s21_.next_;
                    }
                }
                if (((state_0 & 0x200000) != 0 /* is-state_0 doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) */)) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary binary21_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((binary21_arraysLeft__.isArray(leftNodeValue_))) {
                                NV1IntLibrary binary21_arraysRight__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((binary21_arraysRight__.isArray(rightNodeValue_))) {
                                    return this.binary21Boundary(state_0, state_1, leftNodeValue_, rightNodeValue_);
                                }
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
                if (((state_0 & 0x400000) != 0 /* is-state_0 doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) */)) {
                    Binary22Data s23_ = this.binary22_cache;
                    while (s23_ != null) {
                        if ((s23_.arraysLeft_.accepts(leftNodeValue_)) && (s23_.arraysRight_.accepts(rightNodeValue_)) && (s23_.arraysLeft_.isArray(leftNodeValue_)) && (s23_.arraysRight_.isArray(rightNodeValue_))) {
                            return doBinary(leftNodeValue_, rightNodeValue_, s23_.arraysLeft_, s23_.arraysRight_, s23_.loopProfile_);
                        }
                        s23_ = s23_.next_;
                    }
                }
                if (((state_0 & 0x800000) != 0 /* is-state_0 doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) */)) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary binary23_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((binary23_arraysLeft__.isArray(leftNodeValue_))) {
                                NV1RealLibrary binary23_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
                                if ((binary23_arraysRight__.isArray(rightNodeValue_))) {
                                    return this.binary23Boundary(state_0, state_1, leftNodeValue_, rightNodeValue_);
                                }
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
        }
        if (((state_0 & 0xf000000) != 0 /* is-state_0 doBinary(NV1RealJava, NV0Int, LoopConditionProfile) || doBinary(NV1RealJava, NV0Real, LoopConditionProfile) || doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) || doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) */) && leftNodeValue_ instanceof NV1RealJava) {
            NV1RealJava leftNodeValue__ = (NV1RealJava) leftNodeValue_;
            if (((state_0 & 0x1000000) != 0 /* is-state_0 doBinary(NV1RealJava, NV0Int, LoopConditionProfile) */) && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary24_loopProfile_);
            }
            if (((state_0 & 0x2000000) != 0 /* is-state_0 doBinary(NV1RealJava, NV0Real, LoopConditionProfile) */) && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary25_loopProfile_);
            }
            if (((state_0 & 0xc000000) != 0 /* is-state_0 doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) || doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) */)) {
                if (((state_0 & 0x4000000) != 0 /* is-state_0 doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) */)) {
                    Binary26Data s27_ = this.binary26_cache;
                    while (s27_ != null) {
                        if ((s27_.arrays_.accepts(rightNodeValue_)) && (s27_.arrays_.isArray(rightNodeValue_))) {
                            return doBinary(leftNodeValue__, rightNodeValue_, s27_.arrays_, s27_.loopProfile_);
                        }
                        s27_ = s27_.next_;
                    }
                }
                if (((state_0 & 0x8000000) != 0 /* is-state_0 doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) */)) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary binary27_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((binary27_arrays__.isArray(rightNodeValue_))) {
                                return this.binary27Boundary(state_0, state_1, leftNodeValue__, rightNodeValue_);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
        }
        if (((state_0 & 0x70000000) != 0 /* is-state_0 doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int, LoopConditionProfile) || doBinary(Object, Object, NV1RealLibrary, NV1RealLibrary, LoopConditionProfile) || doBinary(Object, Object, NV1RealLibrary, NV1RealLibrary, LoopConditionProfile) */)) {
            if (((state_0 & 0x10000000) != 0 /* is-state_0 doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int, LoopConditionProfile) */)) {
                NV1NV1CachedData s29_ = this.nV1NV1Cached_cache;
                while (s29_ != null) {
                    if ((s29_.arraysLeft_.accepts(leftNodeValue_)) && (s29_.arraysRight_.accepts(rightNodeValue_)) && (s29_.arraysLeft_.isArray(leftNodeValue_)) && (s29_.arraysRight_.isArray(rightNodeValue_)) && (s29_.length_ == s29_.arraysLeft_.length(leftNodeValue_)) && (s29_.length_ == s29_.arraysRight_.length(rightNodeValue_))) {
                        return doNV1NV1Cached(leftNodeValue_, rightNodeValue_, s29_.arraysLeft_, s29_.arraysRight_, s29_.length_, s29_.loopProfile_);
                    }
                    s29_ = s29_.next_;
                }
            }
            if (((state_0 & 0x20000000) != 0 /* is-state_0 doBinary(Object, Object, NV1RealLibrary, NV1RealLibrary, LoopConditionProfile) */)) {
                Binary28Data s30_ = this.binary28_cache;
                while (s30_ != null) {
                    if ((s30_.arraysLeft_.accepts(leftNodeValue_)) && (s30_.arraysRight_.accepts(rightNodeValue_)) && (s30_.arraysLeft_.isArray(leftNodeValue_)) && (s30_.arraysRight_.isArray(rightNodeValue_)) && (s30_.arraysLeft_.length(leftNodeValue_) == s30_.arraysRight_.length(rightNodeValue_))) {
                        return doBinary(leftNodeValue_, rightNodeValue_, s30_.arraysLeft_, s30_.arraysRight_, s30_.loopProfile_);
                    }
                    s30_ = s30_.next_;
                }
            }
            if (((state_0 & 0x40000000) != 0 /* is-state_0 doBinary(Object, Object, NV1RealLibrary, NV1RealLibrary, LoopConditionProfile) */)) {
                EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                Node prev_ = encapsulating_.set(this);
                try {
                    {
                        NV1RealLibrary binary29_arraysLeft__ = (N_V1_REAL_LIBRARY_.getUncached());
                        if ((binary29_arraysLeft__.isArray(leftNodeValue_))) {
                            NV1RealLibrary binary29_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
                            if ((binary29_arraysRight__.isArray(rightNodeValue_)) && (binary29_arraysLeft__.length(leftNodeValue_) == binary29_arraysRight__.length(rightNodeValue_))) {
                                return this.binary29Boundary(state_0, state_1, leftNodeValue_, rightNodeValue_);
                            }
                        }
                    }
                } finally {
                    encapsulating_.set(prev_);
                }
            }
        }
        if (((state_0 & 0x80000000) != 0 /* is-state_0 doBinary(NV2Int, NV0Int, LoopConditionProfile, LoopConditionProfile) */ || (state_1 & 0b11) != 0 /* is-state_1 doBinary(NV2Int, NV0Real, LoopConditionProfile, LoopConditionProfile) || doBinary(NV2Int, NV2Int, LoopConditionProfile, LoopConditionProfile) */) && leftNodeValue_ instanceof NV2Int) {
            NV2Int leftNodeValue__ = (NV2Int) leftNodeValue_;
            if (((state_0 & 0x80000000) != 0 /* is-state_0 doBinary(NV2Int, NV0Int, LoopConditionProfile, LoopConditionProfile) */) && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary30_outerLoopProfile_, this.binary30_innerLoopProfile_);
            }
            if (((state_1 & 0b1) != 0 /* is-state_1 doBinary(NV2Int, NV0Real, LoopConditionProfile, LoopConditionProfile) */) && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary31_outerLoopProfile_, this.binary31_innerLoopProfile_);
            }
            if (((state_1 & 0b10) != 0 /* is-state_1 doBinary(NV2Int, NV2Int, LoopConditionProfile, LoopConditionProfile) */) && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary32_outerLoopProfile_, this.binary32_innerLoopProfile_);
            }
        }
        if (((state_1 & 0b11100) != 0 /* is-state_1 doBinary(NV2Real, NV0Int, LoopConditionProfile, LoopConditionProfile) || doBinary(NV2Real, NV0Real, LoopConditionProfile, LoopConditionProfile) || doBinary(NV2Real, NV2Real, LoopConditionProfile, LoopConditionProfile) */) && leftNodeValue_ instanceof NV2Real) {
            NV2Real leftNodeValue__ = (NV2Real) leftNodeValue_;
            if (((state_1 & 0b100) != 0 /* is-state_1 doBinary(NV2Real, NV0Int, LoopConditionProfile, LoopConditionProfile) */) && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary33_outerLoopProfile_, this.binary33_innerLoopProfile_);
            }
            if (((state_1 & 0b1000) != 0 /* is-state_1 doBinary(NV2Real, NV0Real, LoopConditionProfile, LoopConditionProfile) */) && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary34_outerLoopProfile_, this.binary34_innerLoopProfile_);
            }
            if (((state_1 & 0b10000) != 0 /* is-state_1 doBinary(NV2Real, NV2Real, LoopConditionProfile, LoopConditionProfile) */) && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return doBinary(leftNodeValue__, rightNodeValue__, this.binary35_outerLoopProfile_, this.binary35_innerLoopProfile_);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(leftNodeValue_, rightNodeValue_);
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object binary3Boundary(int state_0, int state_1, NV0Int leftNodeValue__, Object rightNodeValue_) {
        {
            NV1IntLibrary binary3_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return doBinary(leftNodeValue__, rightNodeValue_, binary3_arrays__, this.binary3_loopProfile_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object binary5Boundary(int state_0, int state_1, NV0Int leftNodeValue__, Object rightNodeValue_) {
        {
            NV1RealLibrary binary5_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
            return doBinary(leftNodeValue__, rightNodeValue_, binary5_arrays__, this.binary5_loopProfile_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object binary11Boundary(int state_0, int state_1, NV0Real leftNodeValue__, Object rightNodeValue_) {
        {
            NV1IntLibrary binary11_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return doBinary(leftNodeValue__, rightNodeValue_, binary11_arrays__, this.binary11_loopProfile_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object binary13Boundary(int state_0, int state_1, NV0Real leftNodeValue__, Object rightNodeValue_) {
        {
            NV1RealLibrary binary13_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
            return doBinary(leftNodeValue__, rightNodeValue_, binary13_arrays__, this.binary13_loopProfile_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object binary17Boundary(int state_0, int state_1, Object leftNodeValue_, NV0Int rightNodeValue__) {
        {
            NV1IntLibrary binary17_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return doBinary(leftNodeValue_, rightNodeValue__, binary17_arrays__, this.binary17_loopProfile_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object binary19Boundary(int state_0, int state_1, Object leftNodeValue_, NV0Real rightNodeValue__) {
        {
            NV1IntLibrary binary19_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return doBinary(leftNodeValue_, rightNodeValue__, binary19_arrays__, this.binary19_loopProfile_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object binary21Boundary(int state_0, int state_1, Object leftNodeValue_, Object rightNodeValue_) {
        {
            NV1IntLibrary binary21_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
            NV1IntLibrary binary21_arraysRight__ = (N_V1_INT_LIBRARY_.getUncached());
            return doBinary(leftNodeValue_, rightNodeValue_, binary21_arraysLeft__, binary21_arraysRight__, this.binary21_loopProfile_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object binary23Boundary(int state_0, int state_1, Object leftNodeValue_, Object rightNodeValue_) {
        {
            NV1IntLibrary binary23_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
            NV1RealLibrary binary23_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
            return doBinary(leftNodeValue_, rightNodeValue_, binary23_arraysLeft__, binary23_arraysRight__, this.binary23_loopProfile_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object binary27Boundary(int state_0, int state_1, NV1RealJava leftNodeValue__, Object rightNodeValue_) {
        {
            NV1IntLibrary binary27_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return doBinary(leftNodeValue__, rightNodeValue_, binary27_arrays__, this.binary27_loopProfile_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object binary29Boundary(int state_0, int state_1, Object leftNodeValue_, Object rightNodeValue_) {
        {
            NV1RealLibrary binary29_arraysLeft__ = (N_V1_REAL_LIBRARY_.getUncached());
            NV1RealLibrary binary29_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
            return doBinary(leftNodeValue_, rightNodeValue_, binary29_arraysLeft__, binary29_arraysRight__, this.binary29_loopProfile_);
        }
    }

    private Object executeAndSpecialize(Object leftNodeValue, Object rightNodeValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state_0 = state_0_;
        int state_1 = state_1_;
        int exclude = exclude_;
        try {
            if (leftNodeValue instanceof NV0Int) {
                NV0Int leftNodeValue_ = (NV0Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 doBinary(NV0Int, NV0Int) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 doBinary(NV0Int, NV0Real) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_);
                }
                if (((exclude & 0b1)) == 0 /* is-not-exclude doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) */) {
                    int count3_ = 0;
                    Binary2Data s3_ = this.binary2_cache;
                    if (((state_0 & 0b100) != 0 /* is-state_0 doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) */)) {
                        while (s3_ != null) {
                            if ((s3_.arrays_.accepts(rightNodeValue)) && (s3_.arrays_.isArray(rightNodeValue))) {
                                break;
                            }
                            s3_ = s3_.next_;
                            count3_++;
                        }
                    }
                    if (s3_ == null) {
                        {
                            NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(rightNodeValue)));
                            // assert (s3_.arrays_.accepts(rightNodeValue));
                            if ((arrays__.isArray(rightNodeValue)) && count3_ < (3)) {
                                s3_ = super.insert(new Binary2Data(binary2_cache));
                                s3_.arrays_ = s3_.insertAccessor(arrays__);
                                s3_.loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                this.binary2_cache = s3_;
                                this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) */;
                                this.state_1_ = state_1;
                            }
                        }
                    }
                    if (s3_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return doBinary(leftNodeValue_, rightNodeValue, s3_.arrays_, s3_.loopProfile_);
                    }
                }
                {
                    NV1IntLibrary binary3_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                binary3_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((binary3_arrays__.isArray(rightNodeValue))) {
                                    this.binary3_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                    this.exclude_ = exclude = exclude | 0b1 /* add-exclude doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) */;
                                    this.binary2_cache = null;
                                    state_0 = state_0 & 0xfffffffb /* remove-state_0 doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 doBinary(NV0Int, Object, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_1_ = state_1;
                                    lock.unlock();
                                    hasLock = false;
                                    return doBinary(leftNodeValue_, rightNodeValue, binary3_arrays__, this.binary3_loopProfile_);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
                if (((exclude & 0b10)) == 0 /* is-not-exclude doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) */) {
                    int count5_ = 0;
                    Binary4Data s5_ = this.binary4_cache;
                    if (((state_0 & 0b10000) != 0 /* is-state_0 doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) */)) {
                        while (s5_ != null) {
                            if ((s5_.arrays_.accepts(rightNodeValue)) && (s5_.arrays_.isArray(rightNodeValue))) {
                                break;
                            }
                            s5_ = s5_.next_;
                            count5_++;
                        }
                    }
                    if (s5_ == null) {
                        {
                            NV1RealLibrary arrays__1 = super.insert((N_V1_REAL_LIBRARY_.create(rightNodeValue)));
                            // assert (s5_.arrays_.accepts(rightNodeValue));
                            if ((arrays__1.isArray(rightNodeValue)) && count5_ < (3)) {
                                s5_ = super.insert(new Binary4Data(binary4_cache));
                                s5_.arrays_ = s5_.insertAccessor(arrays__1);
                                s5_.loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                this.binary4_cache = s5_;
                                this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) */;
                                this.state_1_ = state_1;
                            }
                        }
                    }
                    if (s5_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return doBinary(leftNodeValue_, rightNodeValue, s5_.arrays_, s5_.loopProfile_);
                    }
                }
                {
                    NV1RealLibrary binary5_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                binary5_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                                if ((binary5_arrays__.isArray(rightNodeValue))) {
                                    this.binary5_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                    this.exclude_ = exclude = exclude | 0b10 /* add-exclude doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) */;
                                    this.binary4_cache = null;
                                    state_0 = state_0 & 0xffffffef /* remove-state_0 doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) */;
                                    this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 doBinary(NV0Int, Object, NV1RealLibrary, LoopConditionProfile) */;
                                    this.state_1_ = state_1;
                                    lock.unlock();
                                    hasLock = false;
                                    return doBinary(leftNodeValue_, rightNodeValue, binary5_arrays__, this.binary5_loopProfile_);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.binary6_outerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.binary6_innerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0 = state_0 | 0b1000000 /* add-state_0 doBinary(NV0Int, NV2Int, LoopConditionProfile, LoopConditionProfile) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary6_outerLoopProfile_, this.binary6_innerLoopProfile_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.binary7_outerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.binary7_innerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0 = state_0 | 0b10000000 /* add-state_0 doBinary(NV0Int, NV2Real, LoopConditionProfile, LoopConditionProfile) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary7_outerLoopProfile_, this.binary7_innerLoopProfile_);
                }
            }
            if (leftNodeValue instanceof NV0Real) {
                NV0Real leftNodeValue_ = (NV0Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_0_ = state_0 = state_0 | 0b100000000 /* add-state_0 doBinary(NV0Real, NV0Int) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_0_ = state_0 = state_0 | 0b1000000000 /* add-state_0 doBinary(NV0Real, NV0Real) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_);
                }
                if (((exclude & 0b100)) == 0 /* is-not-exclude doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) */) {
                    int count11_ = 0;
                    Binary10Data s11_ = this.binary10_cache;
                    if (((state_0 & 0b10000000000) != 0 /* is-state_0 doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) */)) {
                        while (s11_ != null) {
                            if ((s11_.arrays_.accepts(rightNodeValue)) && (s11_.arrays_.isArray(rightNodeValue))) {
                                break;
                            }
                            s11_ = s11_.next_;
                            count11_++;
                        }
                    }
                    if (s11_ == null) {
                        {
                            NV1IntLibrary arrays__2 = super.insert((N_V1_INT_LIBRARY_.create(rightNodeValue)));
                            // assert (s11_.arrays_.accepts(rightNodeValue));
                            if ((arrays__2.isArray(rightNodeValue)) && count11_ < (3)) {
                                s11_ = super.insert(new Binary10Data(binary10_cache));
                                s11_.arrays_ = s11_.insertAccessor(arrays__2);
                                s11_.loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                this.binary10_cache = s11_;
                                this.state_0_ = state_0 = state_0 | 0b10000000000 /* add-state_0 doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) */;
                                this.state_1_ = state_1;
                            }
                        }
                    }
                    if (s11_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return doBinary(leftNodeValue_, rightNodeValue, s11_.arrays_, s11_.loopProfile_);
                    }
                }
                {
                    NV1IntLibrary binary11_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                binary11_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((binary11_arrays__.isArray(rightNodeValue))) {
                                    this.binary11_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                    this.exclude_ = exclude = exclude | 0b100 /* add-exclude doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) */;
                                    this.binary10_cache = null;
                                    state_0 = state_0 & 0xfffffbff /* remove-state_0 doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_0_ = state_0 = state_0 | 0b100000000000 /* add-state_0 doBinary(NV0Real, Object, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_1_ = state_1;
                                    lock.unlock();
                                    hasLock = false;
                                    return doBinary(leftNodeValue_, rightNodeValue, binary11_arrays__, this.binary11_loopProfile_);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
                if (((exclude & 0b1000)) == 0 /* is-not-exclude doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) */) {
                    int count13_ = 0;
                    Binary12Data s13_ = this.binary12_cache;
                    if (((state_0 & 0b1000000000000) != 0 /* is-state_0 doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) */)) {
                        while (s13_ != null) {
                            if ((s13_.arrays_.accepts(rightNodeValue)) && (s13_.arrays_.isArray(rightNodeValue))) {
                                break;
                            }
                            s13_ = s13_.next_;
                            count13_++;
                        }
                    }
                    if (s13_ == null) {
                        {
                            NV1RealLibrary arrays__3 = super.insert((N_V1_REAL_LIBRARY_.create(rightNodeValue)));
                            // assert (s13_.arrays_.accepts(rightNodeValue));
                            if ((arrays__3.isArray(rightNodeValue)) && count13_ < (3)) {
                                s13_ = super.insert(new Binary12Data(binary12_cache));
                                s13_.arrays_ = s13_.insertAccessor(arrays__3);
                                s13_.loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                this.binary12_cache = s13_;
                                this.state_0_ = state_0 = state_0 | 0b1000000000000 /* add-state_0 doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) */;
                                this.state_1_ = state_1;
                            }
                        }
                    }
                    if (s13_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return doBinary(leftNodeValue_, rightNodeValue, s13_.arrays_, s13_.loopProfile_);
                    }
                }
                {
                    NV1RealLibrary binary13_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                binary13_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                                if ((binary13_arrays__.isArray(rightNodeValue))) {
                                    this.binary13_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                    this.exclude_ = exclude = exclude | 0b1000 /* add-exclude doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) */;
                                    this.binary12_cache = null;
                                    state_0 = state_0 & 0xffffefff /* remove-state_0 doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) */;
                                    this.state_0_ = state_0 = state_0 | 0b10000000000000 /* add-state_0 doBinary(NV0Real, Object, NV1RealLibrary, LoopConditionProfile) */;
                                    this.state_1_ = state_1;
                                    lock.unlock();
                                    hasLock = false;
                                    return doBinary(leftNodeValue_, rightNodeValue, binary13_arrays__, this.binary13_loopProfile_);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.binary14_outerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.binary14_innerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0 = state_0 | 0b100000000000000 /* add-state_0 doBinary(NV0Real, NV2Int, LoopConditionProfile, LoopConditionProfile) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary14_outerLoopProfile_, this.binary14_innerLoopProfile_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.binary15_outerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.binary15_innerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0 = state_0 | 0b1000000000000000 /* add-state_0 doBinary(NV0Real, NV2Real, LoopConditionProfile, LoopConditionProfile) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary15_outerLoopProfile_, this.binary15_innerLoopProfile_);
                }
            }
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                if (((exclude & 0b10000)) == 0 /* is-not-exclude doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) */) {
                    int count17_ = 0;
                    Binary16Data s17_ = this.binary16_cache;
                    if (((state_0 & 0x10000) != 0 /* is-state_0 doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) */)) {
                        while (s17_ != null) {
                            if ((s17_.arrays_.accepts(leftNodeValue)) && (s17_.arrays_.isArray(leftNodeValue))) {
                                break;
                            }
                            s17_ = s17_.next_;
                            count17_++;
                        }
                    }
                    if (s17_ == null) {
                        {
                            NV1IntLibrary arrays__4 = super.insert((N_V1_INT_LIBRARY_.create(leftNodeValue)));
                            // assert (s17_.arrays_.accepts(leftNodeValue));
                            if ((arrays__4.isArray(leftNodeValue)) && count17_ < (3)) {
                                s17_ = super.insert(new Binary16Data(binary16_cache));
                                s17_.arrays_ = s17_.insertAccessor(arrays__4);
                                s17_.loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                this.binary16_cache = s17_;
                                this.state_0_ = state_0 = state_0 | 0x10000 /* add-state_0 doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) */;
                                this.state_1_ = state_1;
                            }
                        }
                    }
                    if (s17_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return doBinary(leftNodeValue, rightNodeValue_, s17_.arrays_, s17_.loopProfile_);
                    }
                }
                {
                    NV1IntLibrary binary17_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                binary17_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((binary17_arrays__.isArray(leftNodeValue))) {
                                    this.binary17_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                    this.exclude_ = exclude = exclude | 0b10000 /* add-exclude doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) */;
                                    this.binary16_cache = null;
                                    state_0 = state_0 & 0xfffeffff /* remove-state_0 doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_0_ = state_0 = state_0 | 0x20000 /* add-state_0 doBinary(Object, NV0Int, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_1_ = state_1;
                                    lock.unlock();
                                    hasLock = false;
                                    return doBinary(leftNodeValue, rightNodeValue_, binary17_arrays__, this.binary17_loopProfile_);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
            }
            if (rightNodeValue instanceof NV0Real) {
                NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                if (((exclude & 0b100000)) == 0 /* is-not-exclude doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) */) {
                    int count19_ = 0;
                    Binary18Data s19_ = this.binary18_cache;
                    if (((state_0 & 0x40000) != 0 /* is-state_0 doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) */)) {
                        while (s19_ != null) {
                            if ((s19_.arrays_.accepts(leftNodeValue)) && (s19_.arrays_.isArray(leftNodeValue))) {
                                break;
                            }
                            s19_ = s19_.next_;
                            count19_++;
                        }
                    }
                    if (s19_ == null) {
                        {
                            NV1IntLibrary arrays__5 = super.insert((N_V1_INT_LIBRARY_.create(leftNodeValue)));
                            // assert (s19_.arrays_.accepts(leftNodeValue));
                            if ((arrays__5.isArray(leftNodeValue)) && count19_ < (3)) {
                                s19_ = super.insert(new Binary18Data(binary18_cache));
                                s19_.arrays_ = s19_.insertAccessor(arrays__5);
                                s19_.loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                this.binary18_cache = s19_;
                                this.state_0_ = state_0 = state_0 | 0x40000 /* add-state_0 doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) */;
                                this.state_1_ = state_1;
                            }
                        }
                    }
                    if (s19_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return doBinary(leftNodeValue, rightNodeValue_, s19_.arrays_, s19_.loopProfile_);
                    }
                }
                {
                    NV1IntLibrary binary19_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                binary19_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((binary19_arrays__.isArray(leftNodeValue))) {
                                    this.binary19_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                    this.exclude_ = exclude = exclude | 0b100000 /* add-exclude doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) */;
                                    this.binary18_cache = null;
                                    state_0 = state_0 & 0xfffbffff /* remove-state_0 doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_0_ = state_0 = state_0 | 0x80000 /* add-state_0 doBinary(Object, NV0Real, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_1_ = state_1;
                                    lock.unlock();
                                    hasLock = false;
                                    return doBinary(leftNodeValue, rightNodeValue_, binary19_arrays__, this.binary19_loopProfile_);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
            }
            if (((exclude & 0b1000000)) == 0 /* is-not-exclude doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) */) {
                int count21_ = 0;
                Binary20Data s21_ = this.binary20_cache;
                if (((state_0 & 0x100000) != 0 /* is-state_0 doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) */)) {
                    while (s21_ != null) {
                        if ((s21_.arraysLeft_.accepts(leftNodeValue)) && (s21_.arraysRight_.accepts(rightNodeValue)) && (s21_.arraysLeft_.isArray(leftNodeValue)) && (s21_.arraysRight_.isArray(rightNodeValue))) {
                            break;
                        }
                        s21_ = s21_.next_;
                        count21_++;
                    }
                }
                if (s21_ == null) {
                    {
                        NV1IntLibrary arraysLeft__ = super.insert((N_V1_INT_LIBRARY_.create(leftNodeValue)));
                        // assert (s21_.arraysLeft_.accepts(leftNodeValue));
                        // assert (s21_.arraysRight_.accepts(rightNodeValue));
                        if ((arraysLeft__.isArray(leftNodeValue))) {
                            NV1IntLibrary arraysRight__ = super.insert((N_V1_INT_LIBRARY_.create(rightNodeValue)));
                            if ((arraysRight__.isArray(rightNodeValue)) && count21_ < (3)) {
                                s21_ = super.insert(new Binary20Data(binary20_cache));
                                s21_.arraysLeft_ = s21_.insertAccessor(arraysLeft__);
                                s21_.arraysRight_ = s21_.insertAccessor(arraysRight__);
                                s21_.loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                this.binary20_cache = s21_;
                                this.state_0_ = state_0 = state_0 | 0x100000 /* add-state_0 doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) */;
                                this.state_1_ = state_1;
                            }
                        }
                    }
                }
                if (s21_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue, rightNodeValue, s21_.arraysLeft_, s21_.arraysRight_, s21_.loopProfile_);
                }
            }
            {
                NV1IntLibrary binary21_arraysRight__ = null;
                NV1IntLibrary binary21_arraysLeft__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            binary21_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((binary21_arraysLeft__.isArray(leftNodeValue))) {
                                binary21_arraysRight__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((binary21_arraysRight__.isArray(rightNodeValue))) {
                                    this.binary21_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                    this.exclude_ = exclude = exclude | 0b1000000 /* add-exclude doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) */;
                                    this.binary20_cache = null;
                                    state_0 = state_0 & 0xffefffff /* remove-state_0 doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_0_ = state_0 = state_0 | 0x200000 /* add-state_0 doBinary(Object, Object, NV1IntLibrary, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_1_ = state_1;
                                    lock.unlock();
                                    hasLock = false;
                                    return doBinary(leftNodeValue, rightNodeValue, binary21_arraysLeft__, binary21_arraysRight__, this.binary21_loopProfile_);
                                }
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b10000000)) == 0 /* is-not-exclude doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) */) {
                int count23_ = 0;
                Binary22Data s23_ = this.binary22_cache;
                if (((state_0 & 0x400000) != 0 /* is-state_0 doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) */)) {
                    while (s23_ != null) {
                        if ((s23_.arraysLeft_.accepts(leftNodeValue)) && (s23_.arraysRight_.accepts(rightNodeValue)) && (s23_.arraysLeft_.isArray(leftNodeValue)) && (s23_.arraysRight_.isArray(rightNodeValue))) {
                            break;
                        }
                        s23_ = s23_.next_;
                        count23_++;
                    }
                }
                if (s23_ == null) {
                    {
                        NV1IntLibrary arraysLeft__1 = super.insert((N_V1_INT_LIBRARY_.create(leftNodeValue)));
                        // assert (s23_.arraysLeft_.accepts(leftNodeValue));
                        // assert (s23_.arraysRight_.accepts(rightNodeValue));
                        if ((arraysLeft__1.isArray(leftNodeValue))) {
                            NV1RealLibrary arraysRight__1 = super.insert((N_V1_REAL_LIBRARY_.create(rightNodeValue)));
                            if ((arraysRight__1.isArray(rightNodeValue)) && count23_ < (3)) {
                                s23_ = super.insert(new Binary22Data(binary22_cache));
                                s23_.arraysLeft_ = s23_.insertAccessor(arraysLeft__1);
                                s23_.arraysRight_ = s23_.insertAccessor(arraysRight__1);
                                s23_.loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                this.binary22_cache = s23_;
                                this.state_0_ = state_0 = state_0 | 0x400000 /* add-state_0 doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) */;
                                this.state_1_ = state_1;
                            }
                        }
                    }
                }
                if (s23_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue, rightNodeValue, s23_.arraysLeft_, s23_.arraysRight_, s23_.loopProfile_);
                }
            }
            {
                NV1RealLibrary binary23_arraysRight__ = null;
                NV1IntLibrary binary23_arraysLeft__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            binary23_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((binary23_arraysLeft__.isArray(leftNodeValue))) {
                                binary23_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
                                if ((binary23_arraysRight__.isArray(rightNodeValue))) {
                                    this.binary23_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                    this.exclude_ = exclude = exclude | 0b10000000 /* add-exclude doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) */;
                                    this.binary22_cache = null;
                                    state_0 = state_0 & 0xffbfffff /* remove-state_0 doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) */;
                                    this.state_0_ = state_0 = state_0 | 0x800000 /* add-state_0 doBinary(Object, Object, NV1IntLibrary, NV1RealLibrary, LoopConditionProfile) */;
                                    this.state_1_ = state_1;
                                    lock.unlock();
                                    hasLock = false;
                                    return doBinary(leftNodeValue, rightNodeValue, binary23_arraysLeft__, binary23_arraysRight__, this.binary23_loopProfile_);
                                }
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (leftNodeValue instanceof NV1RealJava) {
                NV1RealJava leftNodeValue_ = (NV1RealJava) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.binary24_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0 = state_0 | 0x1000000 /* add-state_0 doBinary(NV1RealJava, NV0Int, LoopConditionProfile) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary24_loopProfile_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.binary25_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0 = state_0 | 0x2000000 /* add-state_0 doBinary(NV1RealJava, NV0Real, LoopConditionProfile) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary25_loopProfile_);
                }
                if (((exclude & 0b100000000)) == 0 /* is-not-exclude doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) */) {
                    int count27_ = 0;
                    Binary26Data s27_ = this.binary26_cache;
                    if (((state_0 & 0x4000000) != 0 /* is-state_0 doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) */)) {
                        while (s27_ != null) {
                            if ((s27_.arrays_.accepts(rightNodeValue)) && (s27_.arrays_.isArray(rightNodeValue))) {
                                break;
                            }
                            s27_ = s27_.next_;
                            count27_++;
                        }
                    }
                    if (s27_ == null) {
                        {
                            NV1IntLibrary arrays__6 = super.insert((N_V1_INT_LIBRARY_.create(rightNodeValue)));
                            // assert (s27_.arrays_.accepts(rightNodeValue));
                            if ((arrays__6.isArray(rightNodeValue)) && count27_ < (3)) {
                                s27_ = super.insert(new Binary26Data(binary26_cache));
                                s27_.arrays_ = s27_.insertAccessor(arrays__6);
                                s27_.loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                this.binary26_cache = s27_;
                                this.state_0_ = state_0 = state_0 | 0x4000000 /* add-state_0 doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) */;
                                this.state_1_ = state_1;
                            }
                        }
                    }
                    if (s27_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return doBinary(leftNodeValue_, rightNodeValue, s27_.arrays_, s27_.loopProfile_);
                    }
                }
                {
                    NV1IntLibrary binary27_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                binary27_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((binary27_arrays__.isArray(rightNodeValue))) {
                                    this.binary27_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                    this.exclude_ = exclude = exclude | 0b100000000 /* add-exclude doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) */;
                                    this.binary26_cache = null;
                                    state_0 = state_0 & 0xfbffffff /* remove-state_0 doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_0_ = state_0 = state_0 | 0x8000000 /* add-state_0 doBinary(NV1RealJava, Object, NV1IntLibrary, LoopConditionProfile) */;
                                    this.state_1_ = state_1;
                                    lock.unlock();
                                    hasLock = false;
                                    return doBinary(leftNodeValue_, rightNodeValue, binary27_arrays__, this.binary27_loopProfile_);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
            }
            if (((exclude & 0b1000000000)) == 0 /* is-not-exclude doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int, LoopConditionProfile) */) {
                int count29_ = 0;
                NV1NV1CachedData s29_ = this.nV1NV1Cached_cache;
                if (((state_0 & 0x10000000) != 0 /* is-state_0 doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int, LoopConditionProfile) */)) {
                    while (s29_ != null) {
                        if ((s29_.arraysLeft_.accepts(leftNodeValue)) && (s29_.arraysRight_.accepts(rightNodeValue)) && (s29_.arraysLeft_.isArray(leftNodeValue)) && (s29_.arraysRight_.isArray(rightNodeValue)) && (s29_.length_ == s29_.arraysLeft_.length(leftNodeValue)) && (s29_.length_ == s29_.arraysRight_.length(rightNodeValue))) {
                            break;
                        }
                        s29_ = s29_.next_;
                        count29_++;
                    }
                }
                if (s29_ == null) {
                    {
                        NV1RealLibrary arraysLeft__2 = super.insert((N_V1_REAL_LIBRARY_.create(leftNodeValue)));
                        // assert (s29_.arraysLeft_.accepts(leftNodeValue));
                        // assert (s29_.arraysRight_.accepts(rightNodeValue));
                        if ((arraysLeft__2.isArray(leftNodeValue))) {
                            NV1RealLibrary arraysRight__2 = super.insert((N_V1_REAL_LIBRARY_.create(rightNodeValue)));
                            if ((arraysRight__2.isArray(rightNodeValue))) {
                                int length__ = (arraysLeft__2.length(leftNodeValue));
                                if ((length__ == arraysLeft__2.length(leftNodeValue)) && (length__ == arraysRight__2.length(rightNodeValue)) && count29_ < (3)) {
                                    s29_ = super.insert(new NV1NV1CachedData(nV1NV1Cached_cache));
                                    s29_.arraysLeft_ = s29_.insertAccessor(arraysLeft__2);
                                    s29_.arraysRight_ = s29_.insertAccessor(arraysRight__2);
                                    s29_.length_ = length__;
                                    s29_.loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                    this.nV1NV1Cached_cache = s29_;
                                    this.state_0_ = state_0 = state_0 | 0x10000000 /* add-state_0 doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int, LoopConditionProfile) */;
                                    this.state_1_ = state_1;
                                }
                            }
                        }
                    }
                }
                if (s29_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doNV1NV1Cached(leftNodeValue, rightNodeValue, s29_.arraysLeft_, s29_.arraysRight_, s29_.length_, s29_.loopProfile_);
                }
            }
            if (((exclude & 0b10000000000)) == 0 /* is-not-exclude doBinary(Object, Object, NV1RealLibrary, NV1RealLibrary, LoopConditionProfile) */) {
                int count30_ = 0;
                Binary28Data s30_ = this.binary28_cache;
                if (((state_0 & 0x20000000) != 0 /* is-state_0 doBinary(Object, Object, NV1RealLibrary, NV1RealLibrary, LoopConditionProfile) */)) {
                    while (s30_ != null) {
                        if ((s30_.arraysLeft_.accepts(leftNodeValue)) && (s30_.arraysRight_.accepts(rightNodeValue)) && (s30_.arraysLeft_.isArray(leftNodeValue)) && (s30_.arraysRight_.isArray(rightNodeValue)) && (s30_.arraysLeft_.length(leftNodeValue) == s30_.arraysRight_.length(rightNodeValue))) {
                            break;
                        }
                        s30_ = s30_.next_;
                        count30_++;
                    }
                }
                if (s30_ == null) {
                    {
                        NV1RealLibrary arraysLeft__3 = super.insert((N_V1_REAL_LIBRARY_.create(leftNodeValue)));
                        // assert (s30_.arraysLeft_.accepts(leftNodeValue));
                        // assert (s30_.arraysRight_.accepts(rightNodeValue));
                        if ((arraysLeft__3.isArray(leftNodeValue))) {
                            NV1RealLibrary arraysRight__3 = super.insert((N_V1_REAL_LIBRARY_.create(rightNodeValue)));
                            if ((arraysRight__3.isArray(rightNodeValue)) && (arraysLeft__3.length(leftNodeValue) == arraysRight__3.length(rightNodeValue)) && count30_ < (3)) {
                                s30_ = super.insert(new Binary28Data(binary28_cache));
                                s30_.arraysLeft_ = s30_.insertAccessor(arraysLeft__3);
                                s30_.arraysRight_ = s30_.insertAccessor(arraysRight__3);
                                s30_.loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                this.binary28_cache = s30_;
                                this.exclude_ = exclude = exclude | 0b1000000000 /* add-exclude doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int, LoopConditionProfile) */;
                                this.nV1NV1Cached_cache = null;
                                state_0 = state_0 & 0xefffffff /* remove-state_0 doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int, LoopConditionProfile) */;
                                this.state_0_ = state_0 = state_0 | 0x20000000 /* add-state_0 doBinary(Object, Object, NV1RealLibrary, NV1RealLibrary, LoopConditionProfile) */;
                                this.state_1_ = state_1;
                            }
                        }
                    }
                }
                if (s30_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue, rightNodeValue, s30_.arraysLeft_, s30_.arraysRight_, s30_.loopProfile_);
                }
            }
            {
                NV1RealLibrary binary29_arraysRight__ = null;
                NV1RealLibrary binary29_arraysLeft__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            binary29_arraysLeft__ = (N_V1_REAL_LIBRARY_.getUncached());
                            if ((binary29_arraysLeft__.isArray(leftNodeValue))) {
                                binary29_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
                                if ((binary29_arraysRight__.isArray(rightNodeValue)) && (binary29_arraysLeft__.length(leftNodeValue) == binary29_arraysRight__.length(rightNodeValue))) {
                                    this.binary29_loopProfile_ = (LoopConditionProfile.createCountingProfile());
                                    this.exclude_ = exclude = exclude | 0b11000000000 /* add-exclude doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int, LoopConditionProfile), doBinary(Object, Object, NV1RealLibrary, NV1RealLibrary, LoopConditionProfile) */;
                                    this.nV1NV1Cached_cache = null;
                                    this.binary28_cache = null;
                                    state_0 = state_0 & 0xcfffffff /* remove-state_0 doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int, LoopConditionProfile), doBinary(Object, Object, NV1RealLibrary, NV1RealLibrary, LoopConditionProfile) */;
                                    this.state_0_ = state_0 = state_0 | 0x40000000 /* add-state_0 doBinary(Object, Object, NV1RealLibrary, NV1RealLibrary, LoopConditionProfile) */;
                                    this.state_1_ = state_1;
                                    lock.unlock();
                                    hasLock = false;
                                    return doBinary(leftNodeValue, rightNodeValue, binary29_arraysLeft__, binary29_arraysRight__, this.binary29_loopProfile_);
                                }
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (leftNodeValue instanceof NV2Int) {
                NV2Int leftNodeValue_ = (NV2Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.binary30_outerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.binary30_innerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0 = state_0 | 0x80000000 /* add-state_0 doBinary(NV2Int, NV0Int, LoopConditionProfile, LoopConditionProfile) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary30_outerLoopProfile_, this.binary30_innerLoopProfile_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.binary31_outerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.binary31_innerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0;
                    this.state_1_ = state_1 = state_1 | 0b1 /* add-state_1 doBinary(NV2Int, NV0Real, LoopConditionProfile, LoopConditionProfile) */;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary31_outerLoopProfile_, this.binary31_innerLoopProfile_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.binary32_outerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.binary32_innerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0;
                    this.state_1_ = state_1 = state_1 | 0b10 /* add-state_1 doBinary(NV2Int, NV2Int, LoopConditionProfile, LoopConditionProfile) */;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary32_outerLoopProfile_, this.binary32_innerLoopProfile_);
                }
            }
            if (leftNodeValue instanceof NV2Real) {
                NV2Real leftNodeValue_ = (NV2Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.binary33_outerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.binary33_innerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0;
                    this.state_1_ = state_1 = state_1 | 0b100 /* add-state_1 doBinary(NV2Real, NV0Int, LoopConditionProfile, LoopConditionProfile) */;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary33_outerLoopProfile_, this.binary33_innerLoopProfile_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.binary34_outerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.binary34_innerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0;
                    this.state_1_ = state_1 = state_1 | 0b1000 /* add-state_1 doBinary(NV2Real, NV0Real, LoopConditionProfile, LoopConditionProfile) */;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary34_outerLoopProfile_, this.binary34_innerLoopProfile_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.binary35_outerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.binary35_innerLoopProfile_ = (LoopConditionProfile.createCountingProfile());
                    this.state_0_ = state_0;
                    this.state_1_ = state_1 = state_1 | 0b10000 /* add-state_1 doBinary(NV2Real, NV2Real, LoopConditionProfile, LoopConditionProfile) */;
                    lock.unlock();
                    hasLock = false;
                    return doBinary(leftNodeValue_, rightNodeValue_, this.binary35_outerLoopProfile_, this.binary35_innerLoopProfile_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.leftNode_, this.rightNode_}, leftNodeValue, rightNodeValue);
        } finally {
            if (hasLock) {
                lock.unlock();
            }
        }
    }

    @Override
    public NodeCost getCost() {
        int state_0 = state_0_;
        int state_1 = state_1_;
        if (state_0 == 0 && state_1 == 0) {
            return NodeCost.UNINITIALIZED;
        } else {
            int counter = 0;
            counter += Integer.bitCount(state_0);
            counter += Integer.bitCount(state_1);
            if (counter == 1) {
                Binary2Data s3_ = this.binary2_cache;
                Binary4Data s5_ = this.binary4_cache;
                Binary10Data s11_ = this.binary10_cache;
                Binary12Data s13_ = this.binary12_cache;
                Binary16Data s17_ = this.binary16_cache;
                Binary18Data s19_ = this.binary18_cache;
                Binary20Data s21_ = this.binary20_cache;
                Binary22Data s23_ = this.binary22_cache;
                Binary26Data s27_ = this.binary26_cache;
                NV1NV1CachedData s29_ = this.nV1NV1Cached_cache;
                Binary28Data s30_ = this.binary28_cache;
                if ((s3_ == null || s3_.next_ == null) && (s5_ == null || s5_.next_ == null) && (s11_ == null || s11_.next_ == null) && (s13_ == null || s13_.next_ == null) && (s17_ == null || s17_.next_ == null) && (s19_ == null || s19_.next_ == null) && (s21_ == null || s21_.next_ == null) && (s23_ == null || s23_.next_ == null) && (s27_ == null || s27_.next_ == null) && (s29_ == null || s29_.next_ == null) && (s30_ == null || s30_.next_ == null)) {
                    return NodeCost.MONOMORPHIC;
                }
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaBinaryArithmeticNode create(ArithmeticOperator operator, NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        return new NablaBinaryArithmeticNodeGen(operator, leftNode, rightNode);
    }

    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class Binary2Data extends Node {

        @Child Binary2Data next_;
        @Child NV1IntLibrary arrays_;
        @CompilationFinal LoopConditionProfile loopProfile_;

        Binary2Data(Binary2Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class Binary4Data extends Node {

        @Child Binary4Data next_;
        @Child NV1RealLibrary arrays_;
        @CompilationFinal LoopConditionProfile loopProfile_;

        Binary4Data(Binary4Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class Binary10Data extends Node {

        @Child Binary10Data next_;
        @Child NV1IntLibrary arrays_;
        @CompilationFinal LoopConditionProfile loopProfile_;

        Binary10Data(Binary10Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class Binary12Data extends Node {

        @Child Binary12Data next_;
        @Child NV1RealLibrary arrays_;
        @CompilationFinal LoopConditionProfile loopProfile_;

        Binary12Data(Binary12Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class Binary16Data extends Node {

        @Child Binary16Data next_;
        @Child NV1IntLibrary arrays_;
        @CompilationFinal LoopConditionProfile loopProfile_;

        Binary16Data(Binary16Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class Binary18Data extends Node {

        @Child Binary18Data next_;
        @Child NV1IntLibrary arrays_;
        @CompilationFinal LoopConditionProfile loopProfile_;

        Binary18Data(Binary18Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class Binary20Data extends Node {

        @Child Binary20Data next_;
        @Child NV1IntLibrary arraysLeft_;
        @Child NV1IntLibrary arraysRight_;
        @CompilationFinal LoopConditionProfile loopProfile_;

        Binary20Data(Binary20Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class Binary22Data extends Node {

        @Child Binary22Data next_;
        @Child NV1IntLibrary arraysLeft_;
        @Child NV1RealLibrary arraysRight_;
        @CompilationFinal LoopConditionProfile loopProfile_;

        Binary22Data(Binary22Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class Binary26Data extends Node {

        @Child Binary26Data next_;
        @Child NV1IntLibrary arrays_;
        @CompilationFinal LoopConditionProfile loopProfile_;

        Binary26Data(Binary26Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class NV1NV1CachedData extends Node {

        @Child NV1NV1CachedData next_;
        @Child NV1RealLibrary arraysLeft_;
        @Child NV1RealLibrary arraysRight_;
        @CompilationFinal int length_;
        @CompilationFinal LoopConditionProfile loopProfile_;

        NV1NV1CachedData(NV1NV1CachedData next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class Binary28Data extends Node {

        @Child Binary28Data next_;
        @Child NV1RealLibrary arraysLeft_;
        @Child NV1RealLibrary arraysRight_;
        @CompilationFinal LoopConditionProfile loopProfile_;

        Binary28Data(Binary28Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
}
