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
    @CompilationFinal private volatile long state_;
    @CompilationFinal private volatile int exclude_;
    @Child private Add2Data add2_cache;
    @Child private Add4Data add4_cache;
    @Child private Add10Data add10_cache;
    @Child private Add12Data add12_cache;
    @Child private Add16Data add16_cache;
    @Child private Add18Data add18_cache;
    @Child private Add20Data add20_cache;
    @Child private Add22Data add22_cache;
    @Child private Add26Data add26_cache;
    @Child private NV1NV1CachedData nV1NV1Cached_cache;
    @Child private Add28Data add28_cache;

    private NablaBinaryArithmeticNodeGen(ArithmeticOperator operator, NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        super(operator);
        this.leftNode_ = leftNode;
        this.rightNode_ = rightNode;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        long state = state_;
        Object leftNodeValue_ = this.leftNode_.executeGeneric(frameValue);
        Object rightNodeValue_ = this.rightNode_.executeGeneric(frameValue);
        if ((state & 0b11111111) != 0 /* is-active add(NV0Int, NV0Int) || add(NV0Int, NV0Real) || add(NV0Int, Object, NV1IntLibrary) || add(NV0Int, Object, NV1IntLibrary) || add(NV0Int, Object, NV1RealLibrary) || add(NV0Int, Object, NV1RealLibrary) || add(NV0Int, NV2Int) || add(NV0Int, NV2Real) */ && leftNodeValue_ instanceof NV0Int) {
            NV0Int leftNodeValue__ = (NV0Int) leftNodeValue_;
            if ((state & 0b1) != 0 /* is-active add(NV0Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10) != 0 /* is-active add(NV0Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b111100) != 0 /* is-active add(NV0Int, Object, NV1IntLibrary) || add(NV0Int, Object, NV1IntLibrary) || add(NV0Int, Object, NV1RealLibrary) || add(NV0Int, Object, NV1RealLibrary) */) {
                if ((state & 0b100) != 0 /* is-active add(NV0Int, Object, NV1IntLibrary) */) {
                    Add2Data s3_ = this.add2_cache;
                    while (s3_ != null) {
                        if ((s3_.arrays_.accepts(rightNodeValue_)) && (s3_.arrays_.isArray(rightNodeValue_))) {
                            return add(leftNodeValue__, rightNodeValue_, s3_.arrays_);
                        }
                        s3_ = s3_.next_;
                    }
                }
                if ((state & 0b1000) != 0 /* is-active add(NV0Int, Object, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add3_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((add3_arrays__.isArray(rightNodeValue_))) {
                                return this.add3Boundary(state, leftNodeValue__, rightNodeValue_);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
                if ((state & 0b10000) != 0 /* is-active add(NV0Int, Object, NV1RealLibrary) */) {
                    Add4Data s5_ = this.add4_cache;
                    while (s5_ != null) {
                        if ((s5_.arrays_.accepts(rightNodeValue_)) && (s5_.arrays_.isArray(rightNodeValue_))) {
                            return add(leftNodeValue__, rightNodeValue_, s5_.arrays_);
                        }
                        s5_ = s5_.next_;
                    }
                }
                if ((state & 0b100000) != 0 /* is-active add(NV0Int, Object, NV1RealLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1RealLibrary add5_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                            if ((add5_arrays__.isArray(rightNodeValue_))) {
                                return this.add5Boundary(state, leftNodeValue__, rightNodeValue_);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if ((state & 0b1000000) != 0 /* is-active add(NV0Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000) != 0 /* is-active add(NV0Int, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b1111111100000000) != 0 /* is-active add(NV0Real, NV0Int) || add(NV0Real, NV0Real) || add(NV0Real, Object, NV1IntLibrary) || add(NV0Real, Object, NV1IntLibrary) || add(NV0Real, Object, NV1RealLibrary) || add(NV0Real, Object, NV1RealLibrary) || add(NV0Real, NV2Int) || add(NV0Real, NV2Real) */ && leftNodeValue_ instanceof NV0Real) {
            NV0Real leftNodeValue__ = (NV0Real) leftNodeValue_;
            if ((state & 0b100000000) != 0 /* is-active add(NV0Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000000) != 0 /* is-active add(NV0Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b11110000000000) != 0 /* is-active add(NV0Real, Object, NV1IntLibrary) || add(NV0Real, Object, NV1IntLibrary) || add(NV0Real, Object, NV1RealLibrary) || add(NV0Real, Object, NV1RealLibrary) */) {
                if ((state & 0b10000000000) != 0 /* is-active add(NV0Real, Object, NV1IntLibrary) */) {
                    Add10Data s11_ = this.add10_cache;
                    while (s11_ != null) {
                        if ((s11_.arrays_.accepts(rightNodeValue_)) && (s11_.arrays_.isArray(rightNodeValue_))) {
                            return add(leftNodeValue__, rightNodeValue_, s11_.arrays_);
                        }
                        s11_ = s11_.next_;
                    }
                }
                if ((state & 0b100000000000) != 0 /* is-active add(NV0Real, Object, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add11_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((add11_arrays__.isArray(rightNodeValue_))) {
                                return this.add11Boundary(state, leftNodeValue__, rightNodeValue_);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
                if ((state & 0b1000000000000) != 0 /* is-active add(NV0Real, Object, NV1RealLibrary) */) {
                    Add12Data s13_ = this.add12_cache;
                    while (s13_ != null) {
                        if ((s13_.arrays_.accepts(rightNodeValue_)) && (s13_.arrays_.isArray(rightNodeValue_))) {
                            return add(leftNodeValue__, rightNodeValue_, s13_.arrays_);
                        }
                        s13_ = s13_.next_;
                    }
                }
                if ((state & 0b10000000000000) != 0 /* is-active add(NV0Real, Object, NV1RealLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1RealLibrary add13_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                            if ((add13_arrays__.isArray(rightNodeValue_))) {
                                return this.add13Boundary(state, leftNodeValue__, rightNodeValue_);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if ((state & 0b100000000000000) != 0 /* is-active add(NV0Real, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000000000000) != 0 /* is-active add(NV0Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0xff0000L) != 0 /* is-active add(Object, NV0Int, NV1IntLibrary) || add(Object, NV0Int, NV1IntLibrary) || add(Object, NV0Real, NV1IntLibrary) || add(Object, NV0Real, NV1IntLibrary) || add(Object, Object, NV1IntLibrary, NV1IntLibrary) || add(Object, Object, NV1IntLibrary, NV1IntLibrary) || add(Object, Object, NV1IntLibrary, NV1RealLibrary) || add(Object, Object, NV1IntLibrary, NV1RealLibrary) */) {
            if ((state & 0x30000L) != 0 /* is-active add(Object, NV0Int, NV1IntLibrary) || add(Object, NV0Int, NV1IntLibrary) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                if ((state & 0x10000L) != 0 /* is-active add(Object, NV0Int, NV1IntLibrary) */) {
                    Add16Data s17_ = this.add16_cache;
                    while (s17_ != null) {
                        if ((s17_.arrays_.accepts(leftNodeValue_)) && (s17_.arrays_.isArray(leftNodeValue_))) {
                            return add(leftNodeValue_, rightNodeValue__, s17_.arrays_);
                        }
                        s17_ = s17_.next_;
                    }
                }
                if ((state & 0x20000L) != 0 /* is-active add(Object, NV0Int, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add17_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((add17_arrays__.isArray(leftNodeValue_))) {
                                return this.add17Boundary(state, leftNodeValue_, rightNodeValue__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if ((state & 0xc0000L) != 0 /* is-active add(Object, NV0Real, NV1IntLibrary) || add(Object, NV0Real, NV1IntLibrary) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                if ((state & 0x40000L) != 0 /* is-active add(Object, NV0Real, NV1IntLibrary) */) {
                    Add18Data s19_ = this.add18_cache;
                    while (s19_ != null) {
                        if ((s19_.arrays_.accepts(leftNodeValue_)) && (s19_.arrays_.isArray(leftNodeValue_))) {
                            return add(leftNodeValue_, rightNodeValue__, s19_.arrays_);
                        }
                        s19_ = s19_.next_;
                    }
                }
                if ((state & 0x80000L) != 0 /* is-active add(Object, NV0Real, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add19_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((add19_arrays__.isArray(leftNodeValue_))) {
                                return this.add19Boundary(state, leftNodeValue_, rightNodeValue__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if ((state & 0xf00000L) != 0 /* is-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) || add(Object, Object, NV1IntLibrary, NV1IntLibrary) || add(Object, Object, NV1IntLibrary, NV1RealLibrary) || add(Object, Object, NV1IntLibrary, NV1RealLibrary) */) {
                if ((state & 0x100000L) != 0 /* is-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                    Add20Data s21_ = this.add20_cache;
                    while (s21_ != null) {
                        if ((s21_.arraysLeft_.accepts(leftNodeValue_)) && (s21_.arraysRight_.accepts(rightNodeValue_)) && (s21_.arraysLeft_.isArray(leftNodeValue_)) && (s21_.arraysRight_.isArray(rightNodeValue_))) {
                            return add(leftNodeValue_, rightNodeValue_, s21_.arraysLeft_, s21_.arraysRight_);
                        }
                        s21_ = s21_.next_;
                    }
                }
                if ((state & 0x200000L) != 0 /* is-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add21_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((add21_arraysLeft__.isArray(leftNodeValue_))) {
                                NV1IntLibrary add21_arraysRight__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((add21_arraysRight__.isArray(rightNodeValue_))) {
                                    return this.add21Boundary(state, leftNodeValue_, rightNodeValue_);
                                }
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
                if ((state & 0x400000L) != 0 /* is-active add(Object, Object, NV1IntLibrary, NV1RealLibrary) */) {
                    Add22Data s23_ = this.add22_cache;
                    while (s23_ != null) {
                        if ((s23_.arraysLeft_.accepts(leftNodeValue_)) && (s23_.arraysRight_.accepts(rightNodeValue_)) && (s23_.arraysLeft_.isArray(leftNodeValue_)) && (s23_.arraysRight_.isArray(rightNodeValue_))) {
                            return add(leftNodeValue_, rightNodeValue_, s23_.arraysLeft_, s23_.arraysRight_);
                        }
                        s23_ = s23_.next_;
                    }
                }
                if ((state & 0x800000L) != 0 /* is-active add(Object, Object, NV1IntLibrary, NV1RealLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add23_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((add23_arraysLeft__.isArray(leftNodeValue_))) {
                                NV1RealLibrary add23_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
                                if ((add23_arraysRight__.isArray(rightNodeValue_))) {
                                    return this.add23Boundary(state, leftNodeValue_, rightNodeValue_);
                                }
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
        }
        if ((state & 0xf000000L) != 0 /* is-active add(NV1RealJava, NV0Int) || add(NV1RealJava, NV0Real) || add(NV1RealJava, Object, NV1IntLibrary) || add(NV1RealJava, Object, NV1IntLibrary) */ && leftNodeValue_ instanceof NV1RealJava) {
            NV1RealJava leftNodeValue__ = (NV1RealJava) leftNodeValue_;
            if ((state & 0x1000000L) != 0 /* is-active add(NV1RealJava, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x2000000L) != 0 /* is-active add(NV1RealJava, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0xc000000L) != 0 /* is-active add(NV1RealJava, Object, NV1IntLibrary) || add(NV1RealJava, Object, NV1IntLibrary) */) {
                if ((state & 0x4000000L) != 0 /* is-active add(NV1RealJava, Object, NV1IntLibrary) */) {
                    Add26Data s27_ = this.add26_cache;
                    while (s27_ != null) {
                        if ((s27_.arrays_.accepts(rightNodeValue_)) && (s27_.arrays_.isArray(rightNodeValue_))) {
                            return add(leftNodeValue__, rightNodeValue_, s27_.arrays_);
                        }
                        s27_ = s27_.next_;
                    }
                }
                if ((state & 0x8000000L) != 0 /* is-active add(NV1RealJava, Object, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add27_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((add27_arrays__.isArray(rightNodeValue_))) {
                                return this.add27Boundary(state, leftNodeValue__, rightNodeValue_);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
        }
        if ((state & 0x70000000L) != 0 /* is-active doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int) || add(Object, Object, NV1RealLibrary, NV1RealLibrary) || add(Object, Object, NV1RealLibrary, NV1RealLibrary) */) {
            if ((state & 0x10000000L) != 0 /* is-active doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int) */) {
                NV1NV1CachedData s29_ = this.nV1NV1Cached_cache;
                while (s29_ != null) {
                    if ((s29_.arraysLeft_.accepts(leftNodeValue_)) && (s29_.arraysRight_.accepts(rightNodeValue_)) && (s29_.arraysLeft_.isArray(leftNodeValue_)) && (s29_.arraysRight_.isArray(rightNodeValue_)) && (s29_.length_ == s29_.arraysLeft_.length(leftNodeValue_)) && (s29_.length_ == s29_.arraysRight_.length(rightNodeValue_))) {
                        return doNV1NV1Cached(leftNodeValue_, rightNodeValue_, s29_.arraysLeft_, s29_.arraysRight_, s29_.length_);
                    }
                    s29_ = s29_.next_;
                }
            }
            if ((state & 0x20000000L) != 0 /* is-active add(Object, Object, NV1RealLibrary, NV1RealLibrary) */) {
                Add28Data s30_ = this.add28_cache;
                while (s30_ != null) {
                    if ((s30_.arraysLeft_.accepts(leftNodeValue_)) && (s30_.arraysRight_.accepts(rightNodeValue_)) && (s30_.arraysLeft_.isArray(leftNodeValue_)) && (s30_.arraysRight_.isArray(rightNodeValue_)) && (s30_.arraysLeft_.length(leftNodeValue_) == s30_.arraysRight_.length(rightNodeValue_))) {
                        return add(leftNodeValue_, rightNodeValue_, s30_.arraysLeft_, s30_.arraysRight_);
                    }
                    s30_ = s30_.next_;
                }
            }
            if ((state & 0x40000000L) != 0 /* is-active add(Object, Object, NV1RealLibrary, NV1RealLibrary) */) {
                EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                Node prev_ = encapsulating_.set(this);
                try {
                    {
                        NV1RealLibrary add29_arraysLeft__ = (N_V1_REAL_LIBRARY_.getUncached());
                        if ((add29_arraysLeft__.isArray(leftNodeValue_))) {
                            NV1RealLibrary add29_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
                            if ((add29_arraysRight__.isArray(rightNodeValue_)) && (add29_arraysLeft__.length(leftNodeValue_) == add29_arraysRight__.length(rightNodeValue_))) {
                                return this.add29Boundary(state, leftNodeValue_, rightNodeValue_);
                            }
                        }
                    }
                } finally {
                    encapsulating_.set(prev_);
                }
            }
        }
        if ((state & 0x380000000L) != 0 /* is-active add(NV2Int, NV0Int) || add(NV2Int, NV0Real) || add(NV2Int, NV2Int) */ && leftNodeValue_ instanceof NV2Int) {
            NV2Int leftNodeValue__ = (NV2Int) leftNodeValue_;
            if ((state & 0x80000000L) != 0 /* is-active add(NV2Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x100000000L) != 0 /* is-active add(NV2Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x200000000L) != 0 /* is-active add(NV2Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0x1c00000000L) != 0 /* is-active add(NV2Real, NV0Int) || add(NV2Real, NV0Real) || add(NV2Real, NV2Real) */ && leftNodeValue_ instanceof NV2Real) {
            NV2Real leftNodeValue__ = (NV2Real) leftNodeValue_;
            if ((state & 0x400000000L) != 0 /* is-active add(NV2Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x800000000L) != 0 /* is-active add(NV2Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x1000000000L) != 0 /* is-active add(NV2Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(leftNodeValue_, rightNodeValue_);
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object add3Boundary(long state, NV0Int leftNodeValue__, Object rightNodeValue_) {
        {
            NV1IntLibrary add3_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return add(leftNodeValue__, rightNodeValue_, add3_arrays__);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object add5Boundary(long state, NV0Int leftNodeValue__, Object rightNodeValue_) {
        {
            NV1RealLibrary add5_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
            return add(leftNodeValue__, rightNodeValue_, add5_arrays__);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object add11Boundary(long state, NV0Real leftNodeValue__, Object rightNodeValue_) {
        {
            NV1IntLibrary add11_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return add(leftNodeValue__, rightNodeValue_, add11_arrays__);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object add13Boundary(long state, NV0Real leftNodeValue__, Object rightNodeValue_) {
        {
            NV1RealLibrary add13_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
            return add(leftNodeValue__, rightNodeValue_, add13_arrays__);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object add17Boundary(long state, Object leftNodeValue_, NV0Int rightNodeValue__) {
        {
            NV1IntLibrary add17_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return add(leftNodeValue_, rightNodeValue__, add17_arrays__);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object add19Boundary(long state, Object leftNodeValue_, NV0Real rightNodeValue__) {
        {
            NV1IntLibrary add19_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return add(leftNodeValue_, rightNodeValue__, add19_arrays__);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object add21Boundary(long state, Object leftNodeValue_, Object rightNodeValue_) {
        {
            NV1IntLibrary add21_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
            NV1IntLibrary add21_arraysRight__ = (N_V1_INT_LIBRARY_.getUncached());
            return add(leftNodeValue_, rightNodeValue_, add21_arraysLeft__, add21_arraysRight__);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object add23Boundary(long state, Object leftNodeValue_, Object rightNodeValue_) {
        {
            NV1IntLibrary add23_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
            NV1RealLibrary add23_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
            return add(leftNodeValue_, rightNodeValue_, add23_arraysLeft__, add23_arraysRight__);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object add27Boundary(long state, NV1RealJava leftNodeValue__, Object rightNodeValue_) {
        {
            NV1IntLibrary add27_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return add(leftNodeValue__, rightNodeValue_, add27_arrays__);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object add29Boundary(long state, Object leftNodeValue_, Object rightNodeValue_) {
        {
            NV1RealLibrary add29_arraysLeft__ = (N_V1_REAL_LIBRARY_.getUncached());
            NV1RealLibrary add29_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
            return add(leftNodeValue_, rightNodeValue_, add29_arraysLeft__, add29_arraysRight__);
        }
    }

    private Object executeAndSpecialize(Object leftNodeValue, Object rightNodeValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        long state = state_;
        int exclude = exclude_;
        try {
            if (leftNodeValue instanceof NV0Int) {
                NV0Int leftNodeValue_ = (NV0Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b1 /* add-active add(NV0Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b10 /* add-active add(NV0Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (((exclude & 0b1)) == 0 /* is-not-excluded add(NV0Int, Object, NV1IntLibrary) */) {
                    int count3_ = 0;
                    Add2Data s3_ = this.add2_cache;
                    if ((state & 0b100) != 0 /* is-active add(NV0Int, Object, NV1IntLibrary) */) {
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
                                s3_ = super.insert(new Add2Data(add2_cache));
                                s3_.arrays_ = s3_.insertAccessor(arrays__);
                                this.add2_cache = s3_;
                                this.state_ = state = state | 0b100 /* add-active add(NV0Int, Object, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s3_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue, s3_.arrays_);
                    }
                }
                {
                    NV1IntLibrary add3_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add3_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((add3_arrays__.isArray(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b1 /* add-excluded add(NV0Int, Object, NV1IntLibrary) */;
                                    this.add2_cache = null;
                                    state = state & 0xfffffffffffffffbL /* remove-active add(NV0Int, Object, NV1IntLibrary) */;
                                    this.state_ = state = state | 0b1000 /* add-active add(NV0Int, Object, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue_, rightNodeValue, add3_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
                if (((exclude & 0b10)) == 0 /* is-not-excluded add(NV0Int, Object, NV1RealLibrary) */) {
                    int count5_ = 0;
                    Add4Data s5_ = this.add4_cache;
                    if ((state & 0b10000) != 0 /* is-active add(NV0Int, Object, NV1RealLibrary) */) {
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
                                s5_ = super.insert(new Add4Data(add4_cache));
                                s5_.arrays_ = s5_.insertAccessor(arrays__1);
                                this.add4_cache = s5_;
                                this.state_ = state = state | 0b10000 /* add-active add(NV0Int, Object, NV1RealLibrary) */;
                            }
                        }
                    }
                    if (s5_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue, s5_.arrays_);
                    }
                }
                {
                    NV1RealLibrary add5_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add5_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                                if ((add5_arrays__.isArray(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b10 /* add-excluded add(NV0Int, Object, NV1RealLibrary) */;
                                    this.add4_cache = null;
                                    state = state & 0xffffffffffffffefL /* remove-active add(NV0Int, Object, NV1RealLibrary) */;
                                    this.state_ = state = state | 0b100000 /* add-active add(NV0Int, Object, NV1RealLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue_, rightNodeValue, add5_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0b1000000 /* add-active add(NV0Int, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.state_ = state = state | 0b10000000 /* add-active add(NV0Int, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV0Real) {
                NV0Real leftNodeValue_ = (NV0Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b100000000 /* add-active add(NV0Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b1000000000 /* add-active add(NV0Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (((exclude & 0b100)) == 0 /* is-not-excluded add(NV0Real, Object, NV1IntLibrary) */) {
                    int count11_ = 0;
                    Add10Data s11_ = this.add10_cache;
                    if ((state & 0b10000000000) != 0 /* is-active add(NV0Real, Object, NV1IntLibrary) */) {
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
                                s11_ = super.insert(new Add10Data(add10_cache));
                                s11_.arrays_ = s11_.insertAccessor(arrays__2);
                                this.add10_cache = s11_;
                                this.state_ = state = state | 0b10000000000 /* add-active add(NV0Real, Object, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s11_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue, s11_.arrays_);
                    }
                }
                {
                    NV1IntLibrary add11_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add11_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((add11_arrays__.isArray(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b100 /* add-excluded add(NV0Real, Object, NV1IntLibrary) */;
                                    this.add10_cache = null;
                                    state = state & 0xfffffffffffffbffL /* remove-active add(NV0Real, Object, NV1IntLibrary) */;
                                    this.state_ = state = state | 0b100000000000 /* add-active add(NV0Real, Object, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue_, rightNodeValue, add11_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
                if (((exclude & 0b1000)) == 0 /* is-not-excluded add(NV0Real, Object, NV1RealLibrary) */) {
                    int count13_ = 0;
                    Add12Data s13_ = this.add12_cache;
                    if ((state & 0b1000000000000) != 0 /* is-active add(NV0Real, Object, NV1RealLibrary) */) {
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
                                s13_ = super.insert(new Add12Data(add12_cache));
                                s13_.arrays_ = s13_.insertAccessor(arrays__3);
                                this.add12_cache = s13_;
                                this.state_ = state = state | 0b1000000000000 /* add-active add(NV0Real, Object, NV1RealLibrary) */;
                            }
                        }
                    }
                    if (s13_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue, s13_.arrays_);
                    }
                }
                {
                    NV1RealLibrary add13_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add13_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                                if ((add13_arrays__.isArray(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b1000 /* add-excluded add(NV0Real, Object, NV1RealLibrary) */;
                                    this.add12_cache = null;
                                    state = state & 0xffffffffffffefffL /* remove-active add(NV0Real, Object, NV1RealLibrary) */;
                                    this.state_ = state = state | 0b10000000000000 /* add-active add(NV0Real, Object, NV1RealLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue_, rightNodeValue, add13_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0b100000000000000 /* add-active add(NV0Real, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.state_ = state = state | 0b1000000000000000 /* add-active add(NV0Real, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                if (((exclude & 0b10000)) == 0 /* is-not-excluded add(Object, NV0Int, NV1IntLibrary) */) {
                    int count17_ = 0;
                    Add16Data s17_ = this.add16_cache;
                    if ((state & 0x10000L) != 0 /* is-active add(Object, NV0Int, NV1IntLibrary) */) {
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
                                s17_ = super.insert(new Add16Data(add16_cache));
                                s17_.arrays_ = s17_.insertAccessor(arrays__4);
                                this.add16_cache = s17_;
                                this.state_ = state = state | 0x10000L /* add-active add(Object, NV0Int, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s17_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue, rightNodeValue_, s17_.arrays_);
                    }
                }
                {
                    NV1IntLibrary add17_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add17_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((add17_arrays__.isArray(leftNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b10000 /* add-excluded add(Object, NV0Int, NV1IntLibrary) */;
                                    this.add16_cache = null;
                                    state = state & 0xfffffffffffeffffL /* remove-active add(Object, NV0Int, NV1IntLibrary) */;
                                    this.state_ = state = state | 0x20000L /* add-active add(Object, NV0Int, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue, rightNodeValue_, add17_arrays__);
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
                if (((exclude & 0b100000)) == 0 /* is-not-excluded add(Object, NV0Real, NV1IntLibrary) */) {
                    int count19_ = 0;
                    Add18Data s19_ = this.add18_cache;
                    if ((state & 0x40000L) != 0 /* is-active add(Object, NV0Real, NV1IntLibrary) */) {
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
                                s19_ = super.insert(new Add18Data(add18_cache));
                                s19_.arrays_ = s19_.insertAccessor(arrays__5);
                                this.add18_cache = s19_;
                                this.state_ = state = state | 0x40000L /* add-active add(Object, NV0Real, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s19_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue, rightNodeValue_, s19_.arrays_);
                    }
                }
                {
                    NV1IntLibrary add19_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add19_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((add19_arrays__.isArray(leftNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b100000 /* add-excluded add(Object, NV0Real, NV1IntLibrary) */;
                                    this.add18_cache = null;
                                    state = state & 0xfffffffffffbffffL /* remove-active add(Object, NV0Real, NV1IntLibrary) */;
                                    this.state_ = state = state | 0x80000L /* add-active add(Object, NV0Real, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue, rightNodeValue_, add19_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
            }
            if (((exclude & 0b1000000)) == 0 /* is-not-excluded add(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                int count21_ = 0;
                Add20Data s21_ = this.add20_cache;
                if ((state & 0x100000L) != 0 /* is-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
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
                                s21_ = super.insert(new Add20Data(add20_cache));
                                s21_.arraysLeft_ = s21_.insertAccessor(arraysLeft__);
                                s21_.arraysRight_ = s21_.insertAccessor(arraysRight__);
                                this.add20_cache = s21_;
                                this.state_ = state = state | 0x100000L /* add-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                            }
                        }
                    }
                }
                if (s21_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue, rightNodeValue, s21_.arraysLeft_, s21_.arraysRight_);
                }
            }
            {
                NV1IntLibrary add21_arraysRight__ = null;
                NV1IntLibrary add21_arraysLeft__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            add21_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((add21_arraysLeft__.isArray(leftNodeValue))) {
                                add21_arraysRight__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((add21_arraysRight__.isArray(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b1000000 /* add-excluded add(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                                    this.add20_cache = null;
                                    state = state & 0xffffffffffefffffL /* remove-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                                    this.state_ = state = state | 0x200000L /* add-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue, rightNodeValue, add21_arraysLeft__, add21_arraysRight__);
                                }
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b10000000)) == 0 /* is-not-excluded add(Object, Object, NV1IntLibrary, NV1RealLibrary) */) {
                int count23_ = 0;
                Add22Data s23_ = this.add22_cache;
                if ((state & 0x400000L) != 0 /* is-active add(Object, Object, NV1IntLibrary, NV1RealLibrary) */) {
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
                                s23_ = super.insert(new Add22Data(add22_cache));
                                s23_.arraysLeft_ = s23_.insertAccessor(arraysLeft__1);
                                s23_.arraysRight_ = s23_.insertAccessor(arraysRight__1);
                                this.add22_cache = s23_;
                                this.state_ = state = state | 0x400000L /* add-active add(Object, Object, NV1IntLibrary, NV1RealLibrary) */;
                            }
                        }
                    }
                }
                if (s23_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue, rightNodeValue, s23_.arraysLeft_, s23_.arraysRight_);
                }
            }
            {
                NV1RealLibrary add23_arraysRight__ = null;
                NV1IntLibrary add23_arraysLeft__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            add23_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((add23_arraysLeft__.isArray(leftNodeValue))) {
                                add23_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
                                if ((add23_arraysRight__.isArray(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b10000000 /* add-excluded add(Object, Object, NV1IntLibrary, NV1RealLibrary) */;
                                    this.add22_cache = null;
                                    state = state & 0xffffffffffbfffffL /* remove-active add(Object, Object, NV1IntLibrary, NV1RealLibrary) */;
                                    this.state_ = state = state | 0x800000L /* add-active add(Object, Object, NV1IntLibrary, NV1RealLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue, rightNodeValue, add23_arraysLeft__, add23_arraysRight__);
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
                    this.state_ = state = state | 0x1000000L /* add-active add(NV1RealJava, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x2000000L /* add-active add(NV1RealJava, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (((exclude & 0b100000000)) == 0 /* is-not-excluded add(NV1RealJava, Object, NV1IntLibrary) */) {
                    int count27_ = 0;
                    Add26Data s27_ = this.add26_cache;
                    if ((state & 0x4000000L) != 0 /* is-active add(NV1RealJava, Object, NV1IntLibrary) */) {
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
                                s27_ = super.insert(new Add26Data(add26_cache));
                                s27_.arrays_ = s27_.insertAccessor(arrays__6);
                                this.add26_cache = s27_;
                                this.state_ = state = state | 0x4000000L /* add-active add(NV1RealJava, Object, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s27_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue, s27_.arrays_);
                    }
                }
                {
                    NV1IntLibrary add27_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add27_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                                if ((add27_arrays__.isArray(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b100000000 /* add-excluded add(NV1RealJava, Object, NV1IntLibrary) */;
                                    this.add26_cache = null;
                                    state = state & 0xfffffffffbffffffL /* remove-active add(NV1RealJava, Object, NV1IntLibrary) */;
                                    this.state_ = state = state | 0x8000000L /* add-active add(NV1RealJava, Object, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue_, rightNodeValue, add27_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
            }
            if (((exclude & 0b1000000000)) == 0 /* is-not-excluded doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int) */) {
                int count29_ = 0;
                NV1NV1CachedData s29_ = this.nV1NV1Cached_cache;
                if ((state & 0x10000000L) != 0 /* is-active doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int) */) {
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
                                    this.nV1NV1Cached_cache = s29_;
                                    this.state_ = state = state | 0x10000000L /* add-active doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int) */;
                                }
                            }
                        }
                    }
                }
                if (s29_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doNV1NV1Cached(leftNodeValue, rightNodeValue, s29_.arraysLeft_, s29_.arraysRight_, s29_.length_);
                }
            }
            if (((exclude & 0b10000000000)) == 0 /* is-not-excluded add(Object, Object, NV1RealLibrary, NV1RealLibrary) */) {
                int count30_ = 0;
                Add28Data s30_ = this.add28_cache;
                if ((state & 0x20000000L) != 0 /* is-active add(Object, Object, NV1RealLibrary, NV1RealLibrary) */) {
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
                                s30_ = super.insert(new Add28Data(add28_cache));
                                s30_.arraysLeft_ = s30_.insertAccessor(arraysLeft__3);
                                s30_.arraysRight_ = s30_.insertAccessor(arraysRight__3);
                                this.add28_cache = s30_;
                                this.exclude_ = exclude = exclude | 0b1000000000 /* add-excluded doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int) */;
                                this.nV1NV1Cached_cache = null;
                                state = state & 0xffffffffefffffffL /* remove-active doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int) */;
                                this.state_ = state = state | 0x20000000L /* add-active add(Object, Object, NV1RealLibrary, NV1RealLibrary) */;
                            }
                        }
                    }
                }
                if (s30_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue, rightNodeValue, s30_.arraysLeft_, s30_.arraysRight_);
                }
            }
            {
                NV1RealLibrary add29_arraysRight__ = null;
                NV1RealLibrary add29_arraysLeft__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            add29_arraysLeft__ = (N_V1_REAL_LIBRARY_.getUncached());
                            if ((add29_arraysLeft__.isArray(leftNodeValue))) {
                                add29_arraysRight__ = (N_V1_REAL_LIBRARY_.getUncached());
                                if ((add29_arraysRight__.isArray(rightNodeValue)) && (add29_arraysLeft__.length(leftNodeValue) == add29_arraysRight__.length(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b11000000000 /* add-excluded doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int), add(Object, Object, NV1RealLibrary, NV1RealLibrary) */;
                                    this.nV1NV1Cached_cache = null;
                                    this.add28_cache = null;
                                    state = state & 0xffffffffcfffffffL /* remove-active doNV1NV1Cached(Object, Object, NV1RealLibrary, NV1RealLibrary, int), add(Object, Object, NV1RealLibrary, NV1RealLibrary) */;
                                    this.state_ = state = state | 0x40000000L /* add-active add(Object, Object, NV1RealLibrary, NV1RealLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue, rightNodeValue, add29_arraysLeft__, add29_arraysRight__);
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
                    this.state_ = state = state | 0x80000000L /* add-active add(NV2Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x100000000L /* add-active add(NV2Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0x200000000L /* add-active add(NV2Int, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV2Real) {
                NV2Real leftNodeValue_ = (NV2Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x400000000L /* add-active add(NV2Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x800000000L /* add-active add(NV2Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.state_ = state = state | 0x1000000000L /* add-active add(NV2Real, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
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
        long state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
            Add2Data s3_ = this.add2_cache;
            Add4Data s5_ = this.add4_cache;
            Add10Data s11_ = this.add10_cache;
            Add12Data s13_ = this.add12_cache;
            Add16Data s17_ = this.add16_cache;
            Add18Data s19_ = this.add18_cache;
            Add20Data s21_ = this.add20_cache;
            Add22Data s23_ = this.add22_cache;
            Add26Data s27_ = this.add26_cache;
            NV1NV1CachedData s29_ = this.nV1NV1Cached_cache;
            Add28Data s30_ = this.add28_cache;
            if ((s3_ == null || s3_.next_ == null) && (s5_ == null || s5_.next_ == null) && (s11_ == null || s11_.next_ == null) && (s13_ == null || s13_.next_ == null) && (s17_ == null || s17_.next_ == null) && (s19_ == null || s19_.next_ == null) && (s21_ == null || s21_.next_ == null) && (s23_ == null || s23_.next_ == null) && (s27_ == null || s27_.next_ == null) && (s29_ == null || s29_.next_ == null) && (s30_ == null || s30_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaBinaryArithmeticNode create(ArithmeticOperator operator, NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        return new NablaBinaryArithmeticNodeGen(operator, leftNode, rightNode);
    }

    @GeneratedBy(NablaBinaryArithmeticNode.class)
    private static final class Add2Data extends Node {

        @Child Add2Data next_;
        @Child NV1IntLibrary arrays_;

        Add2Data(Add2Data next_) {
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
    private static final class Add4Data extends Node {

        @Child Add4Data next_;
        @Child NV1RealLibrary arrays_;

        Add4Data(Add4Data next_) {
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
    private static final class Add10Data extends Node {

        @Child Add10Data next_;
        @Child NV1IntLibrary arrays_;

        Add10Data(Add10Data next_) {
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
    private static final class Add12Data extends Node {

        @Child Add12Data next_;
        @Child NV1RealLibrary arrays_;

        Add12Data(Add12Data next_) {
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
    private static final class Add16Data extends Node {

        @Child Add16Data next_;
        @Child NV1IntLibrary arrays_;

        Add16Data(Add16Data next_) {
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
    private static final class Add18Data extends Node {

        @Child Add18Data next_;
        @Child NV1IntLibrary arrays_;

        Add18Data(Add18Data next_) {
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
    private static final class Add20Data extends Node {

        @Child Add20Data next_;
        @Child NV1IntLibrary arraysLeft_;
        @Child NV1IntLibrary arraysRight_;

        Add20Data(Add20Data next_) {
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
    private static final class Add22Data extends Node {

        @Child Add22Data next_;
        @Child NV1IntLibrary arraysLeft_;
        @Child NV1RealLibrary arraysRight_;

        Add22Data(Add22Data next_) {
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
    private static final class Add26Data extends Node {

        @Child Add26Data next_;
        @Child NV1IntLibrary arrays_;

        Add26Data(Add26Data next_) {
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
    private static final class Add28Data extends Node {

        @Child Add28Data next_;
        @Child NV1RealLibrary arraysLeft_;
        @Child NV1RealLibrary arraysRight_;

        Add28Data(Add28Data next_) {
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
