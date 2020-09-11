// CheckStyle: start generated
package fr.cea.nabla.interpreter.values;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.nodes.NodeUtil;
import fr.cea.nabla.interpreter.values.BoxValueNodeTypeLiteral;
import java.util.concurrent.locks.Lock;
import org.graalvm.polyglot.TypeLiteral;

@GeneratedBy(BoxValueNodeTypeLiteral.class)
@SuppressWarnings("unused")
public final class BoxValueNodeTypeLiteralNodeGen extends BoxValueNodeTypeLiteral {

    private static final LibraryFactory<InteropLibrary> INTEROP_LIBRARY_ = LibraryFactory.resolve(InteropLibrary.class);

    @CompilationFinal private int state_;
    @CompilationFinal private int exclude_;
    @Child private Bool0Data bool0_cache;
    @Child private Bool11Data bool11_cache;
    @Child private Bool20Data bool20_cache;
    @Child private Bool30Data bool30_cache;
    @Child private Bool40Data bool40_cache;
    @Child private Int0Data int0_cache;
    @Child private Int11Data int11_cache;
    @Child private Int20Data int20_cache;
    @Child private Int30Data int30_cache;
    @Child private Int40Data int40_cache;
    @Child private Double0Data double0_cache;
    @Child private Double11Data double11_cache;
    @Child private Double20Data double20_cache;
    @Child private Double30Data double30_cache;
    @Child private Double40Data double40_cache;

    private BoxValueNodeTypeLiteralNodeGen(TypeLiteral<?> type) {
        super(type);
    }

    @ExplodeLoop
    @Override
    public Object execute(Object arg0Value) {
        int state = state_;
        if (state != 0 /* is-active doBool(Object, InteropLibrary) || doBool(Object, InteropLibrary) || doBool1(Object, InteropLibrary) || doBool1(Object, InteropLibrary) || doBool2(Object, InteropLibrary) || doBool2(Object, InteropLibrary) || doBool3(Object, InteropLibrary) || doBool3(Object, InteropLibrary) || doBool4(Object, InteropLibrary) || doBool4(Object, InteropLibrary) || doInt(Object, InteropLibrary) || doInt(Object, InteropLibrary) || doInt1(Object, InteropLibrary) || doInt1(Object, InteropLibrary) || doInt2(Object, InteropLibrary) || doInt2(Object, InteropLibrary) || doInt3(Object, InteropLibrary) || doInt3(Object, InteropLibrary) || doInt4(Object, InteropLibrary) || doInt4(Object, InteropLibrary) || doDouble(Object, InteropLibrary) || doDouble(Object, InteropLibrary) || doDouble1(Object, InteropLibrary) || doDouble1(Object, InteropLibrary) || doDouble2(Object, InteropLibrary) || doDouble2(Object, InteropLibrary) || doDouble3(Object, InteropLibrary) || doDouble3(Object, InteropLibrary) || doDouble4(Object, InteropLibrary) || doDouble4(Object, InteropLibrary) */) {
            if ((state & 0b1) != 0 /* is-active doBool(Object, InteropLibrary) */) {
                Bool0Data s1_ = this.bool0_cache;
                while (s1_ != null) {
                    if ((s1_.objLibrary_.accepts(arg0Value))) {
                        assert (isBool());
                        return doBool(arg0Value, s1_.objLibrary_);
                    }
                    s1_ = s1_.next_;
                }
            }
            if ((state & 0b10) != 0 /* is-active doBool(Object, InteropLibrary) */) {
                assert (isBool());
                return this.bool10Boundary(state, arg0Value);
            }
            if ((state & 0b100) != 0 /* is-active doBool1(Object, InteropLibrary) */) {
                Bool11Data s3_ = this.bool11_cache;
                while (s3_ != null) {
                    if ((s3_.objLibrary_.accepts(arg0Value))) {
                        assert (isBoolArray1());
                        return doBool1(arg0Value, s3_.objLibrary_);
                    }
                    s3_ = s3_.next_;
                }
            }
            if ((state & 0b1000) != 0 /* is-active doBool1(Object, InteropLibrary) */) {
                assert (isBoolArray1());
                return this.bool12Boundary(state, arg0Value);
            }
            if ((state & 0b10000) != 0 /* is-active doBool2(Object, InteropLibrary) */) {
                Bool20Data s5_ = this.bool20_cache;
                while (s5_ != null) {
                    if ((s5_.objLibrary_.accepts(arg0Value))) {
                        assert (isBoolArray2());
                        return doBool2(arg0Value, s5_.objLibrary_);
                    }
                    s5_ = s5_.next_;
                }
            }
            if ((state & 0b100000) != 0 /* is-active doBool2(Object, InteropLibrary) */) {
                assert (isBoolArray2());
                return this.bool21Boundary(state, arg0Value);
            }
            if ((state & 0b1000000) != 0 /* is-active doBool3(Object, InteropLibrary) */) {
                Bool30Data s7_ = this.bool30_cache;
                while (s7_ != null) {
                    if ((s7_.objLibrary_.accepts(arg0Value))) {
                        assert (isBoolArray3());
                        return doBool3(arg0Value, s7_.objLibrary_);
                    }
                    s7_ = s7_.next_;
                }
            }
            if ((state & 0b10000000) != 0 /* is-active doBool3(Object, InteropLibrary) */) {
                assert (isBoolArray3());
                return this.bool31Boundary(state, arg0Value);
            }
            if ((state & 0b100000000) != 0 /* is-active doBool4(Object, InteropLibrary) */) {
                Bool40Data s9_ = this.bool40_cache;
                while (s9_ != null) {
                    if ((s9_.objLibrary_.accepts(arg0Value))) {
                        assert (isBoolArray4());
                        return doBool4(arg0Value, s9_.objLibrary_);
                    }
                    s9_ = s9_.next_;
                }
            }
            if ((state & 0b1000000000) != 0 /* is-active doBool4(Object, InteropLibrary) */) {
                assert (isBoolArray4());
                return this.bool41Boundary(state, arg0Value);
            }
            if ((state & 0b10000000000) != 0 /* is-active doInt(Object, InteropLibrary) */) {
                Int0Data s11_ = this.int0_cache;
                while (s11_ != null) {
                    if ((s11_.objLibrary_.accepts(arg0Value))) {
                        assert (isInt());
                        return doInt(arg0Value, s11_.objLibrary_);
                    }
                    s11_ = s11_.next_;
                }
            }
            if ((state & 0b100000000000) != 0 /* is-active doInt(Object, InteropLibrary) */) {
                assert (isInt());
                return this.int10Boundary(state, arg0Value);
            }
            if ((state & 0b1000000000000) != 0 /* is-active doInt1(Object, InteropLibrary) */) {
                Int11Data s13_ = this.int11_cache;
                while (s13_ != null) {
                    if ((s13_.objLibrary_.accepts(arg0Value))) {
                        assert (isIntArray1());
                        return doInt1(arg0Value, s13_.objLibrary_);
                    }
                    s13_ = s13_.next_;
                }
            }
            if ((state & 0b10000000000000) != 0 /* is-active doInt1(Object, InteropLibrary) */) {
                assert (isIntArray1());
                return this.int12Boundary(state, arg0Value);
            }
            if ((state & 0b100000000000000) != 0 /* is-active doInt2(Object, InteropLibrary) */) {
                Int20Data s15_ = this.int20_cache;
                while (s15_ != null) {
                    if ((s15_.objLibrary_.accepts(arg0Value))) {
                        assert (isIntArray2());
                        return doInt2(arg0Value, s15_.objLibrary_);
                    }
                    s15_ = s15_.next_;
                }
            }
            if ((state & 0b1000000000000000) != 0 /* is-active doInt2(Object, InteropLibrary) */) {
                assert (isIntArray2());
                return this.int21Boundary(state, arg0Value);
            }
            if ((state & 0x10000) != 0 /* is-active doInt3(Object, InteropLibrary) */) {
                Int30Data s17_ = this.int30_cache;
                while (s17_ != null) {
                    if ((s17_.objLibrary_.accepts(arg0Value))) {
                        assert (isIntArray3());
                        return doInt3(arg0Value, s17_.objLibrary_);
                    }
                    s17_ = s17_.next_;
                }
            }
            if ((state & 0x20000) != 0 /* is-active doInt3(Object, InteropLibrary) */) {
                assert (isIntArray3());
                return this.int31Boundary(state, arg0Value);
            }
            if ((state & 0x40000) != 0 /* is-active doInt4(Object, InteropLibrary) */) {
                Int40Data s19_ = this.int40_cache;
                while (s19_ != null) {
                    if ((s19_.objLibrary_.accepts(arg0Value))) {
                        assert (isIntArray4());
                        return doInt4(arg0Value, s19_.objLibrary_);
                    }
                    s19_ = s19_.next_;
                }
            }
            if ((state & 0x80000) != 0 /* is-active doInt4(Object, InteropLibrary) */) {
                assert (isIntArray4());
                return this.int41Boundary(state, arg0Value);
            }
            if ((state & 0x100000) != 0 /* is-active doDouble(Object, InteropLibrary) */) {
                Double0Data s21_ = this.double0_cache;
                while (s21_ != null) {
                    if ((s21_.objLibrary_.accepts(arg0Value))) {
                        assert (isDouble());
                        return doDouble(arg0Value, s21_.objLibrary_);
                    }
                    s21_ = s21_.next_;
                }
            }
            if ((state & 0x200000) != 0 /* is-active doDouble(Object, InteropLibrary) */) {
                assert (isDouble());
                return this.double10Boundary(state, arg0Value);
            }
            if ((state & 0x400000) != 0 /* is-active doDouble1(Object, InteropLibrary) */) {
                Double11Data s23_ = this.double11_cache;
                while (s23_ != null) {
                    if ((s23_.objLibrary_.accepts(arg0Value))) {
                        assert (isDoubleArray1());
                        return doDouble1(arg0Value, s23_.objLibrary_);
                    }
                    s23_ = s23_.next_;
                }
            }
            if ((state & 0x800000) != 0 /* is-active doDouble1(Object, InteropLibrary) */) {
                assert (isDoubleArray1());
                return this.double12Boundary(state, arg0Value);
            }
            if ((state & 0x1000000) != 0 /* is-active doDouble2(Object, InteropLibrary) */) {
                Double20Data s25_ = this.double20_cache;
                while (s25_ != null) {
                    if ((s25_.objLibrary_.accepts(arg0Value))) {
                        assert (isDoubleArray2());
                        return doDouble2(arg0Value, s25_.objLibrary_);
                    }
                    s25_ = s25_.next_;
                }
            }
            if ((state & 0x2000000) != 0 /* is-active doDouble2(Object, InteropLibrary) */) {
                assert (isDoubleArray2());
                return this.double21Boundary(state, arg0Value);
            }
            if ((state & 0x4000000) != 0 /* is-active doDouble3(Object, InteropLibrary) */) {
                Double30Data s27_ = this.double30_cache;
                while (s27_ != null) {
                    if ((s27_.objLibrary_.accepts(arg0Value))) {
                        assert (isDoubleArray3());
                        return doDouble3(arg0Value, s27_.objLibrary_);
                    }
                    s27_ = s27_.next_;
                }
            }
            if ((state & 0x8000000) != 0 /* is-active doDouble3(Object, InteropLibrary) */) {
                assert (isDoubleArray3());
                return this.double31Boundary(state, arg0Value);
            }
            if ((state & 0x10000000) != 0 /* is-active doDouble4(Object, InteropLibrary) */) {
                Double40Data s29_ = this.double40_cache;
                while (s29_ != null) {
                    if ((s29_.objLibrary_.accepts(arg0Value))) {
                        assert (isDoubleArray4());
                        return doDouble4(arg0Value, s29_.objLibrary_);
                    }
                    s29_ = s29_.next_;
                }
            }
            if ((state & 0x20000000) != 0 /* is-active doDouble4(Object, InteropLibrary) */) {
                assert (isDoubleArray4());
                return this.double41Boundary(state, arg0Value);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(arg0Value);
    }

    @TruffleBoundary
    private Object bool10Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doBool(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object bool12Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doBool1(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object bool21Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doBool2(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object bool31Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doBool3(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object bool41Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doBool4(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object int10Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doInt(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object int12Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doInt1(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object int21Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doInt2(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object int31Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doInt3(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object int41Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doInt4(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object double10Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doDouble(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object double12Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doDouble1(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object double21Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doDouble2(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object double31Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doDouble3(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    @TruffleBoundary
    private Object double41Boundary(int state, Object arg0Value) {
        Node prev_ = NodeUtil.pushEncapsulatingNode(this);
        try {
            return doDouble4(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
        } finally {
            NodeUtil.popEncapsulatingNode(prev_);
        }
    }

    private Object executeAndSpecialize(Object arg0Value) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        int exclude = exclude_;
        try {
            if (((exclude & 0b1)) == 0 /* is-not-excluded doBool(Object, InteropLibrary) */) {
                int count1_ = 0;
                Bool0Data s1_ = this.bool0_cache;
                if ((state & 0b1) != 0 /* is-active doBool(Object, InteropLibrary) */) {
                    while (s1_ != null) {
                        if ((s1_.objLibrary_.accepts(arg0Value))) {
                            assert (isBool());
                            break;
                        }
                        s1_ = s1_.next_;
                        count1_++;
                    }
                }
                if (s1_ == null) {
                    if ((isBool()) && count1_ < (3)) {
                        // assert (s1_.objLibrary_.accepts(arg0Value));
                        s1_ = super.insert(new Bool0Data(bool0_cache));
                        s1_.objLibrary_ = s1_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.bool0_cache = s1_;
                        this.state_ = state = state | 0b1 /* add-active doBool(Object, InteropLibrary) */;
                    }
                }
                if (s1_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBool(arg0Value, s1_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isBool())) {
                        this.exclude_ = exclude = exclude | 0b1 /* add-excluded doBool(Object, InteropLibrary) */;
                        this.bool0_cache = null;
                        state = state & 0xfffffffe /* remove-active doBool(Object, InteropLibrary) */;
                        this.state_ = state = state | 0b10 /* add-active doBool(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doBool(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b10)) == 0 /* is-not-excluded doBool1(Object, InteropLibrary) */) {
                int count3_ = 0;
                Bool11Data s3_ = this.bool11_cache;
                if ((state & 0b100) != 0 /* is-active doBool1(Object, InteropLibrary) */) {
                    while (s3_ != null) {
                        if ((s3_.objLibrary_.accepts(arg0Value))) {
                            assert (isBoolArray1());
                            break;
                        }
                        s3_ = s3_.next_;
                        count3_++;
                    }
                }
                if (s3_ == null) {
                    if ((isBoolArray1()) && count3_ < (3)) {
                        // assert (s3_.objLibrary_.accepts(arg0Value));
                        s3_ = super.insert(new Bool11Data(bool11_cache));
                        s3_.objLibrary_ = s3_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.bool11_cache = s3_;
                        this.state_ = state = state | 0b100 /* add-active doBool1(Object, InteropLibrary) */;
                    }
                }
                if (s3_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBool1(arg0Value, s3_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isBoolArray1())) {
                        this.exclude_ = exclude = exclude | 0b10 /* add-excluded doBool1(Object, InteropLibrary) */;
                        this.bool11_cache = null;
                        state = state & 0xfffffffb /* remove-active doBool1(Object, InteropLibrary) */;
                        this.state_ = state = state | 0b1000 /* add-active doBool1(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doBool1(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b100)) == 0 /* is-not-excluded doBool2(Object, InteropLibrary) */) {
                int count5_ = 0;
                Bool20Data s5_ = this.bool20_cache;
                if ((state & 0b10000) != 0 /* is-active doBool2(Object, InteropLibrary) */) {
                    while (s5_ != null) {
                        if ((s5_.objLibrary_.accepts(arg0Value))) {
                            assert (isBoolArray2());
                            break;
                        }
                        s5_ = s5_.next_;
                        count5_++;
                    }
                }
                if (s5_ == null) {
                    if ((isBoolArray2()) && count5_ < (3)) {
                        // assert (s5_.objLibrary_.accepts(arg0Value));
                        s5_ = super.insert(new Bool20Data(bool20_cache));
                        s5_.objLibrary_ = s5_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.bool20_cache = s5_;
                        this.state_ = state = state | 0b10000 /* add-active doBool2(Object, InteropLibrary) */;
                    }
                }
                if (s5_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBool2(arg0Value, s5_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isBoolArray2())) {
                        this.exclude_ = exclude = exclude | 0b100 /* add-excluded doBool2(Object, InteropLibrary) */;
                        this.bool20_cache = null;
                        state = state & 0xffffffef /* remove-active doBool2(Object, InteropLibrary) */;
                        this.state_ = state = state | 0b100000 /* add-active doBool2(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doBool2(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b1000)) == 0 /* is-not-excluded doBool3(Object, InteropLibrary) */) {
                int count7_ = 0;
                Bool30Data s7_ = this.bool30_cache;
                if ((state & 0b1000000) != 0 /* is-active doBool3(Object, InteropLibrary) */) {
                    while (s7_ != null) {
                        if ((s7_.objLibrary_.accepts(arg0Value))) {
                            assert (isBoolArray3());
                            break;
                        }
                        s7_ = s7_.next_;
                        count7_++;
                    }
                }
                if (s7_ == null) {
                    if ((isBoolArray3()) && count7_ < (3)) {
                        // assert (s7_.objLibrary_.accepts(arg0Value));
                        s7_ = super.insert(new Bool30Data(bool30_cache));
                        s7_.objLibrary_ = s7_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.bool30_cache = s7_;
                        this.state_ = state = state | 0b1000000 /* add-active doBool3(Object, InteropLibrary) */;
                    }
                }
                if (s7_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBool3(arg0Value, s7_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isBoolArray3())) {
                        this.exclude_ = exclude = exclude | 0b1000 /* add-excluded doBool3(Object, InteropLibrary) */;
                        this.bool30_cache = null;
                        state = state & 0xffffffbf /* remove-active doBool3(Object, InteropLibrary) */;
                        this.state_ = state = state | 0b10000000 /* add-active doBool3(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doBool3(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b10000)) == 0 /* is-not-excluded doBool4(Object, InteropLibrary) */) {
                int count9_ = 0;
                Bool40Data s9_ = this.bool40_cache;
                if ((state & 0b100000000) != 0 /* is-active doBool4(Object, InteropLibrary) */) {
                    while (s9_ != null) {
                        if ((s9_.objLibrary_.accepts(arg0Value))) {
                            assert (isBoolArray4());
                            break;
                        }
                        s9_ = s9_.next_;
                        count9_++;
                    }
                }
                if (s9_ == null) {
                    if ((isBoolArray4()) && count9_ < (3)) {
                        // assert (s9_.objLibrary_.accepts(arg0Value));
                        s9_ = super.insert(new Bool40Data(bool40_cache));
                        s9_.objLibrary_ = s9_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.bool40_cache = s9_;
                        this.state_ = state = state | 0b100000000 /* add-active doBool4(Object, InteropLibrary) */;
                    }
                }
                if (s9_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBool4(arg0Value, s9_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isBoolArray4())) {
                        this.exclude_ = exclude = exclude | 0b10000 /* add-excluded doBool4(Object, InteropLibrary) */;
                        this.bool40_cache = null;
                        state = state & 0xfffffeff /* remove-active doBool4(Object, InteropLibrary) */;
                        this.state_ = state = state | 0b1000000000 /* add-active doBool4(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doBool4(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b100000)) == 0 /* is-not-excluded doInt(Object, InteropLibrary) */) {
                int count11_ = 0;
                Int0Data s11_ = this.int0_cache;
                if ((state & 0b10000000000) != 0 /* is-active doInt(Object, InteropLibrary) */) {
                    while (s11_ != null) {
                        if ((s11_.objLibrary_.accepts(arg0Value))) {
                            assert (isInt());
                            break;
                        }
                        s11_ = s11_.next_;
                        count11_++;
                    }
                }
                if (s11_ == null) {
                    if ((isInt()) && count11_ < (3)) {
                        // assert (s11_.objLibrary_.accepts(arg0Value));
                        s11_ = super.insert(new Int0Data(int0_cache));
                        s11_.objLibrary_ = s11_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.int0_cache = s11_;
                        this.state_ = state = state | 0b10000000000 /* add-active doInt(Object, InteropLibrary) */;
                    }
                }
                if (s11_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInt(arg0Value, s11_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isInt())) {
                        this.exclude_ = exclude = exclude | 0b100000 /* add-excluded doInt(Object, InteropLibrary) */;
                        this.int0_cache = null;
                        state = state & 0xfffffbff /* remove-active doInt(Object, InteropLibrary) */;
                        this.state_ = state = state | 0b100000000000 /* add-active doInt(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doInt(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b1000000)) == 0 /* is-not-excluded doInt1(Object, InteropLibrary) */) {
                int count13_ = 0;
                Int11Data s13_ = this.int11_cache;
                if ((state & 0b1000000000000) != 0 /* is-active doInt1(Object, InteropLibrary) */) {
                    while (s13_ != null) {
                        if ((s13_.objLibrary_.accepts(arg0Value))) {
                            assert (isIntArray1());
                            break;
                        }
                        s13_ = s13_.next_;
                        count13_++;
                    }
                }
                if (s13_ == null) {
                    if ((isIntArray1()) && count13_ < (3)) {
                        // assert (s13_.objLibrary_.accepts(arg0Value));
                        s13_ = super.insert(new Int11Data(int11_cache));
                        s13_.objLibrary_ = s13_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.int11_cache = s13_;
                        this.state_ = state = state | 0b1000000000000 /* add-active doInt1(Object, InteropLibrary) */;
                    }
                }
                if (s13_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInt1(arg0Value, s13_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isIntArray1())) {
                        this.exclude_ = exclude = exclude | 0b1000000 /* add-excluded doInt1(Object, InteropLibrary) */;
                        this.int11_cache = null;
                        state = state & 0xffffefff /* remove-active doInt1(Object, InteropLibrary) */;
                        this.state_ = state = state | 0b10000000000000 /* add-active doInt1(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doInt1(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b10000000)) == 0 /* is-not-excluded doInt2(Object, InteropLibrary) */) {
                int count15_ = 0;
                Int20Data s15_ = this.int20_cache;
                if ((state & 0b100000000000000) != 0 /* is-active doInt2(Object, InteropLibrary) */) {
                    while (s15_ != null) {
                        if ((s15_.objLibrary_.accepts(arg0Value))) {
                            assert (isIntArray2());
                            break;
                        }
                        s15_ = s15_.next_;
                        count15_++;
                    }
                }
                if (s15_ == null) {
                    if ((isIntArray2()) && count15_ < (3)) {
                        // assert (s15_.objLibrary_.accepts(arg0Value));
                        s15_ = super.insert(new Int20Data(int20_cache));
                        s15_.objLibrary_ = s15_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.int20_cache = s15_;
                        this.state_ = state = state | 0b100000000000000 /* add-active doInt2(Object, InteropLibrary) */;
                    }
                }
                if (s15_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInt2(arg0Value, s15_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isIntArray2())) {
                        this.exclude_ = exclude = exclude | 0b10000000 /* add-excluded doInt2(Object, InteropLibrary) */;
                        this.int20_cache = null;
                        state = state & 0xffffbfff /* remove-active doInt2(Object, InteropLibrary) */;
                        this.state_ = state = state | 0b1000000000000000 /* add-active doInt2(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doInt2(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b100000000)) == 0 /* is-not-excluded doInt3(Object, InteropLibrary) */) {
                int count17_ = 0;
                Int30Data s17_ = this.int30_cache;
                if ((state & 0x10000) != 0 /* is-active doInt3(Object, InteropLibrary) */) {
                    while (s17_ != null) {
                        if ((s17_.objLibrary_.accepts(arg0Value))) {
                            assert (isIntArray3());
                            break;
                        }
                        s17_ = s17_.next_;
                        count17_++;
                    }
                }
                if (s17_ == null) {
                    if ((isIntArray3()) && count17_ < (3)) {
                        // assert (s17_.objLibrary_.accepts(arg0Value));
                        s17_ = super.insert(new Int30Data(int30_cache));
                        s17_.objLibrary_ = s17_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.int30_cache = s17_;
                        this.state_ = state = state | 0x10000 /* add-active doInt3(Object, InteropLibrary) */;
                    }
                }
                if (s17_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInt3(arg0Value, s17_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isIntArray3())) {
                        this.exclude_ = exclude = exclude | 0b100000000 /* add-excluded doInt3(Object, InteropLibrary) */;
                        this.int30_cache = null;
                        state = state & 0xfffeffff /* remove-active doInt3(Object, InteropLibrary) */;
                        this.state_ = state = state | 0x20000 /* add-active doInt3(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doInt3(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b1000000000)) == 0 /* is-not-excluded doInt4(Object, InteropLibrary) */) {
                int count19_ = 0;
                Int40Data s19_ = this.int40_cache;
                if ((state & 0x40000) != 0 /* is-active doInt4(Object, InteropLibrary) */) {
                    while (s19_ != null) {
                        if ((s19_.objLibrary_.accepts(arg0Value))) {
                            assert (isIntArray4());
                            break;
                        }
                        s19_ = s19_.next_;
                        count19_++;
                    }
                }
                if (s19_ == null) {
                    if ((isIntArray4()) && count19_ < (3)) {
                        // assert (s19_.objLibrary_.accepts(arg0Value));
                        s19_ = super.insert(new Int40Data(int40_cache));
                        s19_.objLibrary_ = s19_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.int40_cache = s19_;
                        this.state_ = state = state | 0x40000 /* add-active doInt4(Object, InteropLibrary) */;
                    }
                }
                if (s19_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInt4(arg0Value, s19_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isIntArray4())) {
                        this.exclude_ = exclude = exclude | 0b1000000000 /* add-excluded doInt4(Object, InteropLibrary) */;
                        this.int40_cache = null;
                        state = state & 0xfffbffff /* remove-active doInt4(Object, InteropLibrary) */;
                        this.state_ = state = state | 0x80000 /* add-active doInt4(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doInt4(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b10000000000)) == 0 /* is-not-excluded doDouble(Object, InteropLibrary) */) {
                int count21_ = 0;
                Double0Data s21_ = this.double0_cache;
                if ((state & 0x100000) != 0 /* is-active doDouble(Object, InteropLibrary) */) {
                    while (s21_ != null) {
                        if ((s21_.objLibrary_.accepts(arg0Value))) {
                            assert (isDouble());
                            break;
                        }
                        s21_ = s21_.next_;
                        count21_++;
                    }
                }
                if (s21_ == null) {
                    if ((isDouble()) && count21_ < (3)) {
                        // assert (s21_.objLibrary_.accepts(arg0Value));
                        s21_ = super.insert(new Double0Data(double0_cache));
                        s21_.objLibrary_ = s21_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.double0_cache = s21_;
                        this.state_ = state = state | 0x100000 /* add-active doDouble(Object, InteropLibrary) */;
                    }
                }
                if (s21_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doDouble(arg0Value, s21_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isDouble())) {
                        this.exclude_ = exclude = exclude | 0b10000000000 /* add-excluded doDouble(Object, InteropLibrary) */;
                        this.double0_cache = null;
                        state = state & 0xffefffff /* remove-active doDouble(Object, InteropLibrary) */;
                        this.state_ = state = state | 0x200000 /* add-active doDouble(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doDouble(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b100000000000)) == 0 /* is-not-excluded doDouble1(Object, InteropLibrary) */) {
                int count23_ = 0;
                Double11Data s23_ = this.double11_cache;
                if ((state & 0x400000) != 0 /* is-active doDouble1(Object, InteropLibrary) */) {
                    while (s23_ != null) {
                        if ((s23_.objLibrary_.accepts(arg0Value))) {
                            assert (isDoubleArray1());
                            break;
                        }
                        s23_ = s23_.next_;
                        count23_++;
                    }
                }
                if (s23_ == null) {
                    if ((isDoubleArray1()) && count23_ < (3)) {
                        // assert (s23_.objLibrary_.accepts(arg0Value));
                        s23_ = super.insert(new Double11Data(double11_cache));
                        s23_.objLibrary_ = s23_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.double11_cache = s23_;
                        this.state_ = state = state | 0x400000 /* add-active doDouble1(Object, InteropLibrary) */;
                    }
                }
                if (s23_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doDouble1(arg0Value, s23_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isDoubleArray1())) {
                        this.exclude_ = exclude = exclude | 0b100000000000 /* add-excluded doDouble1(Object, InteropLibrary) */;
                        this.double11_cache = null;
                        state = state & 0xffbfffff /* remove-active doDouble1(Object, InteropLibrary) */;
                        this.state_ = state = state | 0x800000 /* add-active doDouble1(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doDouble1(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b1000000000000)) == 0 /* is-not-excluded doDouble2(Object, InteropLibrary) */) {
                int count25_ = 0;
                Double20Data s25_ = this.double20_cache;
                if ((state & 0x1000000) != 0 /* is-active doDouble2(Object, InteropLibrary) */) {
                    while (s25_ != null) {
                        if ((s25_.objLibrary_.accepts(arg0Value))) {
                            assert (isDoubleArray2());
                            break;
                        }
                        s25_ = s25_.next_;
                        count25_++;
                    }
                }
                if (s25_ == null) {
                    if ((isDoubleArray2()) && count25_ < (3)) {
                        // assert (s25_.objLibrary_.accepts(arg0Value));
                        s25_ = super.insert(new Double20Data(double20_cache));
                        s25_.objLibrary_ = s25_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.double20_cache = s25_;
                        this.state_ = state = state | 0x1000000 /* add-active doDouble2(Object, InteropLibrary) */;
                    }
                }
                if (s25_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doDouble2(arg0Value, s25_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isDoubleArray2())) {
                        this.exclude_ = exclude = exclude | 0b1000000000000 /* add-excluded doDouble2(Object, InteropLibrary) */;
                        this.double20_cache = null;
                        state = state & 0xfeffffff /* remove-active doDouble2(Object, InteropLibrary) */;
                        this.state_ = state = state | 0x2000000 /* add-active doDouble2(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doDouble2(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b10000000000000)) == 0 /* is-not-excluded doDouble3(Object, InteropLibrary) */) {
                int count27_ = 0;
                Double30Data s27_ = this.double30_cache;
                if ((state & 0x4000000) != 0 /* is-active doDouble3(Object, InteropLibrary) */) {
                    while (s27_ != null) {
                        if ((s27_.objLibrary_.accepts(arg0Value))) {
                            assert (isDoubleArray3());
                            break;
                        }
                        s27_ = s27_.next_;
                        count27_++;
                    }
                }
                if (s27_ == null) {
                    if ((isDoubleArray3()) && count27_ < (3)) {
                        // assert (s27_.objLibrary_.accepts(arg0Value));
                        s27_ = super.insert(new Double30Data(double30_cache));
                        s27_.objLibrary_ = s27_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.double30_cache = s27_;
                        this.state_ = state = state | 0x4000000 /* add-active doDouble3(Object, InteropLibrary) */;
                    }
                }
                if (s27_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doDouble3(arg0Value, s27_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isDoubleArray3())) {
                        this.exclude_ = exclude = exclude | 0b10000000000000 /* add-excluded doDouble3(Object, InteropLibrary) */;
                        this.double30_cache = null;
                        state = state & 0xfbffffff /* remove-active doDouble3(Object, InteropLibrary) */;
                        this.state_ = state = state | 0x8000000 /* add-active doDouble3(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doDouble3(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (((exclude & 0b100000000000000)) == 0 /* is-not-excluded doDouble4(Object, InteropLibrary) */) {
                int count29_ = 0;
                Double40Data s29_ = this.double40_cache;
                if ((state & 0x10000000) != 0 /* is-active doDouble4(Object, InteropLibrary) */) {
                    while (s29_ != null) {
                        if ((s29_.objLibrary_.accepts(arg0Value))) {
                            assert (isDoubleArray4());
                            break;
                        }
                        s29_ = s29_.next_;
                        count29_++;
                    }
                }
                if (s29_ == null) {
                    if ((isDoubleArray4()) && count29_ < (3)) {
                        // assert (s29_.objLibrary_.accepts(arg0Value));
                        s29_ = super.insert(new Double40Data(double40_cache));
                        s29_.objLibrary_ = s29_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.double40_cache = s29_;
                        this.state_ = state = state | 0x10000000 /* add-active doDouble4(Object, InteropLibrary) */;
                    }
                }
                if (s29_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doDouble4(arg0Value, s29_.objLibrary_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((isDoubleArray4())) {
                        this.exclude_ = exclude = exclude | 0b100000000000000 /* add-excluded doDouble4(Object, InteropLibrary) */;
                        this.double40_cache = null;
                        state = state & 0xefffffff /* remove-active doDouble4(Object, InteropLibrary) */;
                        this.state_ = state = state | 0x20000000 /* add-active doDouble4(Object, InteropLibrary) */;
                        lock.unlock();
                        hasLock = false;
                        return doDouble4(arg0Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {null}, arg0Value);
        } finally {
            if (hasLock) {
                lock.unlock();
            }
        }
    }

    @Override
    public NodeCost getCost() {
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
            Bool0Data s1_ = this.bool0_cache;
            Bool11Data s3_ = this.bool11_cache;
            Bool20Data s5_ = this.bool20_cache;
            Bool30Data s7_ = this.bool30_cache;
            Bool40Data s9_ = this.bool40_cache;
            Int0Data s11_ = this.int0_cache;
            Int11Data s13_ = this.int11_cache;
            Int20Data s15_ = this.int20_cache;
            Int30Data s17_ = this.int30_cache;
            Int40Data s19_ = this.int40_cache;
            Double0Data s21_ = this.double0_cache;
            Double11Data s23_ = this.double11_cache;
            Double20Data s25_ = this.double20_cache;
            Double30Data s27_ = this.double30_cache;
            Double40Data s29_ = this.double40_cache;
            if ((s1_ == null || s1_.next_ == null) && (s3_ == null || s3_.next_ == null) && (s5_ == null || s5_.next_ == null) && (s7_ == null || s7_.next_ == null) && (s9_ == null || s9_.next_ == null) && (s11_ == null || s11_.next_ == null) && (s13_ == null || s13_.next_ == null) && (s15_ == null || s15_.next_ == null) && (s17_ == null || s17_.next_ == null) && (s19_ == null || s19_.next_ == null) && (s21_ == null || s21_.next_ == null) && (s23_ == null || s23_.next_ == null) && (s25_ == null || s25_.next_ == null) && (s27_ == null || s27_.next_ == null) && (s29_ == null || s29_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static BoxValueNodeTypeLiteral create(TypeLiteral<?> type) {
        return new BoxValueNodeTypeLiteralNodeGen(type);
    }

    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Bool0Data extends Node {

        @Child Bool0Data next_;
        @Child InteropLibrary objLibrary_;

        Bool0Data(Bool0Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Bool11Data extends Node {

        @Child Bool11Data next_;
        @Child InteropLibrary objLibrary_;

        Bool11Data(Bool11Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Bool20Data extends Node {

        @Child Bool20Data next_;
        @Child InteropLibrary objLibrary_;

        Bool20Data(Bool20Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Bool30Data extends Node {

        @Child Bool30Data next_;
        @Child InteropLibrary objLibrary_;

        Bool30Data(Bool30Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Bool40Data extends Node {

        @Child Bool40Data next_;
        @Child InteropLibrary objLibrary_;

        Bool40Data(Bool40Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Int0Data extends Node {

        @Child Int0Data next_;
        @Child InteropLibrary objLibrary_;

        Int0Data(Int0Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Int11Data extends Node {

        @Child Int11Data next_;
        @Child InteropLibrary objLibrary_;

        Int11Data(Int11Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Int20Data extends Node {

        @Child Int20Data next_;
        @Child InteropLibrary objLibrary_;

        Int20Data(Int20Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Int30Data extends Node {

        @Child Int30Data next_;
        @Child InteropLibrary objLibrary_;

        Int30Data(Int30Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Int40Data extends Node {

        @Child Int40Data next_;
        @Child InteropLibrary objLibrary_;

        Int40Data(Int40Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Double0Data extends Node {

        @Child Double0Data next_;
        @Child InteropLibrary objLibrary_;

        Double0Data(Double0Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Double11Data extends Node {

        @Child Double11Data next_;
        @Child InteropLibrary objLibrary_;

        Double11Data(Double11Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Double20Data extends Node {

        @Child Double20Data next_;
        @Child InteropLibrary objLibrary_;

        Double20Data(Double20Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Double30Data extends Node {

        @Child Double30Data next_;
        @Child InteropLibrary objLibrary_;

        Double30Data(Double30Data next_) {
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
    @GeneratedBy(BoxValueNodeTypeLiteral.class)
    private static final class Double40Data extends Node {

        @Child Double40Data next_;
        @Child InteropLibrary objLibrary_;

        Double40Data(Double40Data next_) {
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
