// CheckStyle: start generated
package fr.cea.nabla.interpreter.values;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.TruffleLanguage.ContextReference;
import com.oracle.truffle.api.TruffleLanguage.Env;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.EncapsulatingNodeReference;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.runtime.NablaContext;
import java.util.concurrent.locks.Lock;
import org.graalvm.polyglot.Value;

@GeneratedBy(BoxValueNode.class)
@SuppressWarnings("unused")
public final class BoxValueNodeGen extends BoxValueNode {

    private static final LibraryFactory<InteropLibrary> INTEROP_LIBRARY_ = LibraryFactory.resolve(InteropLibrary.class);

    @CompilationFinal private volatile int state_0_;
    @CompilationFinal private volatile int state_1_;
    @CompilationFinal private volatile int exclude_;
    @CompilationFinal private ContextReference<NablaContext> nablaLanguageContextReference_;
    @CompilationFinal private HostObjectData hostObject_cache;
    @Child private Int0Data int0_cache;
    @Child private Int11Data int11_cache;
    @Child private Int20Data int20_cache;
    @Child private Int30Data int30_cache;
    @Child private Int40Data int40_cache;
    @Child private Double10Data double10_cache;
    @Child private Double11Data double11_cache;
    @Child private Double21Data double21_cache;
    @Child private Double30Data double30_cache;
    @Child private Double40Data double40_cache;
    @Child private Bool0Data bool0_cache;
    @Child private Bool11Data bool11_cache;
    @Child private Bool20Data bool20_cache;
    @Child private Bool30Data bool30_cache;
    @Child private Bool40Data bool40_cache;

    private BoxValueNodeGen(Class<?> type) {
        super(type);
    }

    @ExplodeLoop
    @Override
    public Object execute(Object arg0Value) {
        int state_0 = state_0_;
        int state_1 = state_1_;
        if (((state_0 & 0b1) != 0 /* is-state_0 doHostObjectValue(Value) */) && arg0Value instanceof Value) {
            Value arg0Value_ = (Value) arg0Value;
            if ((arg0Value_.isHostObject())) {
                return doHostObjectValue(arg0Value_);
            }
        }
        if (((state_0 & 0b10) != 0 /* is-state_0 doHostObject(Object, NablaContext, Env) */)) {
            HostObjectData s2_ = this.hostObject_cache;
            while (s2_ != null) {
                {
                    ContextReference<NablaContext> nablaLanguageContextReference__ = this.nablaLanguageContextReference_;
                    NablaContext context__ = nablaLanguageContextReference__.get();
                    if ((s2_.env_.isHostObject(arg0Value))) {
                        return doHostObject(arg0Value, context__, s2_.env_);
                    }
                }
                s2_ = s2_.next_;
            }
        }
        if (((state_0 & 0b1100) != 0 /* is-state_0 doIntValue(Value) || doInt1Value(Value) */) && arg0Value instanceof Value) {
            Value arg0Value_ = (Value) arg0Value;
            if (((state_0 & 0b100) != 0 /* is-state_0 doIntValue(Value) */)) {
                assert (isInt());
                return doIntValue(arg0Value_);
            }
            if (((state_0 & 0b1000) != 0 /* is-state_0 doInt1Value(Value) */)) {
                assert (isIntArray1());
                return doInt1Value(arg0Value_);
            }
        }
        if (((state_0 & 0b11111111110000) != 0 /* is-state_0 doInt(Object, InteropLibrary) || doInt(Object, InteropLibrary) || doInt1(Object, InteropLibrary) || doInt1(Object, InteropLibrary) || doInt2(Object, InteropLibrary) || doInt2(Object, InteropLibrary) || doInt3(Object, InteropLibrary) || doInt3(Object, InteropLibrary) || doInt4(Object, InteropLibrary) || doInt4(Object, InteropLibrary) */)) {
            if (((state_0 & 0b10000) != 0 /* is-state_0 doInt(Object, InteropLibrary) */)) {
                Int0Data s5_ = this.int0_cache;
                while (s5_ != null) {
                    if ((s5_.objLibrary_.accepts(arg0Value))) {
                        assert (isInt());
                        return doInt(arg0Value, s5_.objLibrary_);
                    }
                    s5_ = s5_.next_;
                }
            }
            if (((state_0 & 0b100000) != 0 /* is-state_0 doInt(Object, InteropLibrary) */)) {
                assert (isInt());
                return this.int10Boundary(state_0, state_1, arg0Value);
            }
            if (((state_0 & 0b1000000) != 0 /* is-state_0 doInt1(Object, InteropLibrary) */)) {
                Int11Data s7_ = this.int11_cache;
                while (s7_ != null) {
                    if ((s7_.objLibrary_.accepts(arg0Value))) {
                        assert (isIntArray1());
                        return doInt1(arg0Value, s7_.objLibrary_);
                    }
                    s7_ = s7_.next_;
                }
            }
            if (((state_0 & 0b10000000) != 0 /* is-state_0 doInt1(Object, InteropLibrary) */)) {
                assert (isIntArray1());
                return this.int12Boundary(state_0, state_1, arg0Value);
            }
            if (((state_0 & 0b100000000) != 0 /* is-state_0 doInt2(Object, InteropLibrary) */)) {
                Int20Data s9_ = this.int20_cache;
                while (s9_ != null) {
                    if ((s9_.objLibrary_.accepts(arg0Value))) {
                        assert (isIntArray2());
                        return doInt2(arg0Value, s9_.objLibrary_);
                    }
                    s9_ = s9_.next_;
                }
            }
            if (((state_0 & 0b1000000000) != 0 /* is-state_0 doInt2(Object, InteropLibrary) */)) {
                assert (isIntArray2());
                return this.int21Boundary(state_0, state_1, arg0Value);
            }
            if (((state_0 & 0b10000000000) != 0 /* is-state_0 doInt3(Object, InteropLibrary) */)) {
                Int30Data s11_ = this.int30_cache;
                while (s11_ != null) {
                    if ((s11_.objLibrary_.accepts(arg0Value))) {
                        assert (isIntArray3());
                        return doInt3(arg0Value, s11_.objLibrary_);
                    }
                    s11_ = s11_.next_;
                }
            }
            if (((state_0 & 0b100000000000) != 0 /* is-state_0 doInt3(Object, InteropLibrary) */)) {
                assert (isIntArray3());
                return this.int31Boundary(state_0, state_1, arg0Value);
            }
            if (((state_0 & 0b1000000000000) != 0 /* is-state_0 doInt4(Object, InteropLibrary) */)) {
                Int40Data s13_ = this.int40_cache;
                while (s13_ != null) {
                    if ((s13_.objLibrary_.accepts(arg0Value))) {
                        assert (isIntArray4());
                        return doInt4(arg0Value, s13_.objLibrary_);
                    }
                    s13_ = s13_.next_;
                }
            }
            if (((state_0 & 0b10000000000000) != 0 /* is-state_0 doInt4(Object, InteropLibrary) */)) {
                assert (isIntArray4());
                return this.int41Boundary(state_0, state_1, arg0Value);
            }
        }
        if (((state_0 & 0b1100000000000000) != 0 /* is-state_0 doDouble(Value) || doDouble1Value(Value) */) && arg0Value instanceof Value) {
            Value arg0Value_ = (Value) arg0Value;
            if (((state_0 & 0b100000000000000) != 0 /* is-state_0 doDouble(Value) */)) {
                assert (isDouble());
                return doDouble(arg0Value_);
            }
            if (((state_0 & 0b1000000000000000) != 0 /* is-state_0 doDouble1Value(Value) */)) {
                assert (isDoubleArray1());
                return doDouble1Value(arg0Value_);
            }
        }
        if (((state_0 & 0x3ff0000) != 0 /* is-state_0 doDouble(Object, InteropLibrary) || doDouble(Object, InteropLibrary) || doDouble1(Object, InteropLibrary) || doDouble1(Object, InteropLibrary) || doDouble2(Object, InteropLibrary) || doDouble2(Object, InteropLibrary) || doDouble3(Object, InteropLibrary) || doDouble3(Object, InteropLibrary) || doDouble4(Object, InteropLibrary) || doDouble4(Object, InteropLibrary) */)) {
            if (((state_0 & 0x10000) != 0 /* is-state_0 doDouble(Object, InteropLibrary) */)) {
                Double10Data s17_ = this.double10_cache;
                while (s17_ != null) {
                    if ((s17_.objLibrary_.accepts(arg0Value))) {
                        assert (isDouble());
                        return doDouble(arg0Value, s17_.objLibrary_);
                    }
                    s17_ = s17_.next_;
                }
            }
            if (((state_0 & 0x20000) != 0 /* is-state_0 doDouble(Object, InteropLibrary) */)) {
                assert (isDouble());
                return this.double20Boundary(state_0, state_1, arg0Value);
            }
            if (((state_0 & 0x40000) != 0 /* is-state_0 doDouble1(Object, InteropLibrary) */)) {
                Double11Data s19_ = this.double11_cache;
                while (s19_ != null) {
                    if ((s19_.objLibrary_.accepts(arg0Value))) {
                        assert (isDoubleArray1());
                        return doDouble1(arg0Value, s19_.objLibrary_);
                    }
                    s19_ = s19_.next_;
                }
            }
            if (((state_0 & 0x80000) != 0 /* is-state_0 doDouble1(Object, InteropLibrary) */)) {
                assert (isDoubleArray1());
                return this.double12Boundary(state_0, state_1, arg0Value);
            }
            if (((state_0 & 0x100000) != 0 /* is-state_0 doDouble2(Object, InteropLibrary) */)) {
                Double21Data s21_ = this.double21_cache;
                while (s21_ != null) {
                    if ((s21_.objLibrary_.accepts(arg0Value))) {
                        assert (isDoubleArray2());
                        return doDouble2(arg0Value, s21_.objLibrary_);
                    }
                    s21_ = s21_.next_;
                }
            }
            if (((state_0 & 0x200000) != 0 /* is-state_0 doDouble2(Object, InteropLibrary) */)) {
                assert (isDoubleArray2());
                return this.double22Boundary(state_0, state_1, arg0Value);
            }
            if (((state_0 & 0x400000) != 0 /* is-state_0 doDouble3(Object, InteropLibrary) */)) {
                Double30Data s23_ = this.double30_cache;
                while (s23_ != null) {
                    if ((s23_.objLibrary_.accepts(arg0Value))) {
                        assert (isDoubleArray3());
                        return doDouble3(arg0Value, s23_.objLibrary_);
                    }
                    s23_ = s23_.next_;
                }
            }
            if (((state_0 & 0x800000) != 0 /* is-state_0 doDouble3(Object, InteropLibrary) */)) {
                assert (isDoubleArray3());
                return this.double31Boundary(state_0, state_1, arg0Value);
            }
            if (((state_0 & 0x1000000) != 0 /* is-state_0 doDouble4(Object, InteropLibrary) */)) {
                Double40Data s25_ = this.double40_cache;
                while (s25_ != null) {
                    if ((s25_.objLibrary_.accepts(arg0Value))) {
                        assert (isDoubleArray4());
                        return doDouble4(arg0Value, s25_.objLibrary_);
                    }
                    s25_ = s25_.next_;
                }
            }
            if (((state_0 & 0x2000000) != 0 /* is-state_0 doDouble4(Object, InteropLibrary) */)) {
                assert (isDoubleArray4());
                return this.double41Boundary(state_0, state_1, arg0Value);
            }
        }
        if (((state_0 & 0x4000000) != 0 /* is-state_0 doBoolValue(Value) */) && arg0Value instanceof Value) {
            Value arg0Value_ = (Value) arg0Value;
            assert (isBool());
            return doBoolValue(arg0Value_);
        }
        if (((state_0 & 0xf8000000) != 0 /* is-state_0 doBool(Object, InteropLibrary) || doBool(Object, InteropLibrary) || doBool1(Object, InteropLibrary) || doBool1(Object, InteropLibrary) || doBool2(Object, InteropLibrary) */ || state_1 != 0 /* is-state_1 doBool2(Object, InteropLibrary) || doBool3(Object, InteropLibrary) || doBool3(Object, InteropLibrary) || doBool4(Object, InteropLibrary) || doBool4(Object, InteropLibrary) */)) {
            if (((state_0 & 0x8000000) != 0 /* is-state_0 doBool(Object, InteropLibrary) */)) {
                Bool0Data s28_ = this.bool0_cache;
                while (s28_ != null) {
                    if ((s28_.objLibrary_.accepts(arg0Value))) {
                        assert (isBool());
                        return doBool(arg0Value, s28_.objLibrary_);
                    }
                    s28_ = s28_.next_;
                }
            }
            if (((state_0 & 0x10000000) != 0 /* is-state_0 doBool(Object, InteropLibrary) */)) {
                assert (isBool());
                return this.bool10Boundary(state_0, state_1, arg0Value);
            }
            if (((state_0 & 0x20000000) != 0 /* is-state_0 doBool1(Object, InteropLibrary) */)) {
                Bool11Data s30_ = this.bool11_cache;
                while (s30_ != null) {
                    if ((s30_.objLibrary_.accepts(arg0Value))) {
                        assert (isBoolArray1());
                        return doBool1(arg0Value, s30_.objLibrary_);
                    }
                    s30_ = s30_.next_;
                }
            }
            if (((state_0 & 0x40000000) != 0 /* is-state_0 doBool1(Object, InteropLibrary) */)) {
                assert (isBoolArray1());
                return this.bool12Boundary(state_0, state_1, arg0Value);
            }
            if (((state_0 & 0x80000000) != 0 /* is-state_0 doBool2(Object, InteropLibrary) */)) {
                Bool20Data s32_ = this.bool20_cache;
                while (s32_ != null) {
                    if ((s32_.objLibrary_.accepts(arg0Value))) {
                        assert (isBoolArray2());
                        return doBool2(arg0Value, s32_.objLibrary_);
                    }
                    s32_ = s32_.next_;
                }
            }
            if (((state_1 & 0b1) != 0 /* is-state_1 doBool2(Object, InteropLibrary) */)) {
                assert (isBoolArray2());
                return this.bool21Boundary(state_0, state_1, arg0Value);
            }
            if (((state_1 & 0b10) != 0 /* is-state_1 doBool3(Object, InteropLibrary) */)) {
                Bool30Data s34_ = this.bool30_cache;
                while (s34_ != null) {
                    if ((s34_.objLibrary_.accepts(arg0Value))) {
                        assert (isBoolArray3());
                        return doBool3(arg0Value, s34_.objLibrary_);
                    }
                    s34_ = s34_.next_;
                }
            }
            if (((state_1 & 0b100) != 0 /* is-state_1 doBool3(Object, InteropLibrary) */)) {
                assert (isBoolArray3());
                return this.bool31Boundary(state_0, state_1, arg0Value);
            }
            if (((state_1 & 0b1000) != 0 /* is-state_1 doBool4(Object, InteropLibrary) */)) {
                Bool40Data s36_ = this.bool40_cache;
                while (s36_ != null) {
                    if ((s36_.objLibrary_.accepts(arg0Value))) {
                        assert (isBoolArray4());
                        return doBool4(arg0Value, s36_.objLibrary_);
                    }
                    s36_ = s36_.next_;
                }
            }
            if (((state_1 & 0b10000) != 0 /* is-state_1 doBool4(Object, InteropLibrary) */)) {
                assert (isBoolArray4());
                return this.bool41Boundary(state_0, state_1, arg0Value);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(arg0Value);
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object int10Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary int10_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doInt(arg0Value, int10_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object int12Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary int12_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doInt1(arg0Value, int12_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object int21Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary int21_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doInt2(arg0Value, int21_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object int31Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary int31_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doInt3(arg0Value, int31_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object int41Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary int41_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doInt4(arg0Value, int41_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object double20Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary double20_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doDouble(arg0Value, double20_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object double12Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary double12_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doDouble1(arg0Value, double12_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object double22Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary double22_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doDouble2(arg0Value, double22_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object double31Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary double31_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doDouble3(arg0Value, double31_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object double41Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary double41_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doDouble4(arg0Value, double41_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object bool10Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary bool10_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doBool(arg0Value, bool10_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object bool12Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary bool12_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doBool1(arg0Value, bool12_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object bool21Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary bool21_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doBool2(arg0Value, bool21_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object bool31Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary bool31_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doBool3(arg0Value, bool31_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object bool41Boundary(int state_0, int state_1, Object arg0Value) {
        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
        Node prev_ = encapsulating_.set(this);
        try {
            {
                InteropLibrary bool41_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                return doBool4(arg0Value, bool41_objLibrary__);
            }
        } finally {
            encapsulating_.set(prev_);
        }
    }

    private Object executeAndSpecialize(Object arg0Value) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state_0 = state_0_;
        int state_1 = state_1_;
        int exclude = exclude_;
        try {
            if (arg0Value instanceof Value) {
                Value arg0Value_ = (Value) arg0Value;
                if ((arg0Value_.isHostObject())) {
                    this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 doHostObjectValue(Value) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doHostObjectValue(arg0Value_);
                }
            }
            {
                NablaContext context__ = null;
                int count2_ = 0;
                HostObjectData s2_ = this.hostObject_cache;
                if (((state_0 & 0b10) != 0 /* is-state_0 doHostObject(Object, NablaContext, Env) */)) {
                    while (s2_ != null) {
                        {
                            ContextReference<NablaContext> nablaLanguageContextReference__1 = this.nablaLanguageContextReference_;
                            if (nablaLanguageContextReference__1 == null) {
                                this.nablaLanguageContextReference_ = nablaLanguageContextReference__1 = super.lookupContextReference(NablaLanguage.class);
                            }
                            context__ = nablaLanguageContextReference__1.get();
                            if ((s2_.env_.isHostObject(arg0Value))) {
                                break;
                            }
                        }
                        s2_ = s2_.next_;
                        count2_++;
                    }
                }
                if (s2_ == null) {
                    {
                        ContextReference<NablaContext> nablaLanguageContextReference__2 = this.nablaLanguageContextReference_;
                        if (nablaLanguageContextReference__2 == null) {
                            this.nablaLanguageContextReference_ = nablaLanguageContextReference__2 = super.lookupContextReference(NablaLanguage.class);
                        }
                        context__ = nablaLanguageContextReference__2.get();
                        Env env__ = (context__.getEnv());
                        if ((env__.isHostObject(arg0Value)) && count2_ < (3)) {
                            s2_ = new HostObjectData(hostObject_cache);
                            s2_.env_ = env__;
                            this.hostObject_cache = s2_;
                            this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 doHostObject(Object, NablaContext, Env) */;
                            this.state_1_ = state_1;
                        }
                    }
                }
                if (s2_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doHostObject(arg0Value, context__, s2_.env_);
                }
            }
            if (arg0Value instanceof Value) {
                Value arg0Value_ = (Value) arg0Value;
                if ((isInt())) {
                    this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 doIntValue(Value) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doIntValue(arg0Value_);
                }
                if ((isIntArray1())) {
                    this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 doInt1Value(Value) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doInt1Value(arg0Value_);
                }
            }
            if (((exclude & 0b1)) == 0 /* is-not-exclude doInt(Object, InteropLibrary) */) {
                int count5_ = 0;
                Int0Data s5_ = this.int0_cache;
                if (((state_0 & 0b10000) != 0 /* is-state_0 doInt(Object, InteropLibrary) */)) {
                    while (s5_ != null) {
                        if ((s5_.objLibrary_.accepts(arg0Value))) {
                            assert (isInt());
                            break;
                        }
                        s5_ = s5_.next_;
                        count5_++;
                    }
                }
                if (s5_ == null) {
                    if ((isInt()) && count5_ < (3)) {
                        // assert (s5_.objLibrary_.accepts(arg0Value));
                        s5_ = super.insert(new Int0Data(int0_cache));
                        s5_.objLibrary_ = s5_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.int0_cache = s5_;
                        this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 doInt(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s5_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInt(arg0Value, s5_.objLibrary_);
                }
            }
            {
                InteropLibrary int10_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isInt())) {
                            int10_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b1 /* add-exclude doInt(Object, InteropLibrary) */;
                            this.int0_cache = null;
                            state_0 = state_0 & 0xffffffef /* remove-state_0 doInt(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 doInt(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doInt(arg0Value, int10_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b10)) == 0 /* is-not-exclude doInt1(Object, InteropLibrary) */) {
                int count7_ = 0;
                Int11Data s7_ = this.int11_cache;
                if (((state_0 & 0b1000000) != 0 /* is-state_0 doInt1(Object, InteropLibrary) */)) {
                    while (s7_ != null) {
                        if ((s7_.objLibrary_.accepts(arg0Value))) {
                            assert (isIntArray1());
                            break;
                        }
                        s7_ = s7_.next_;
                        count7_++;
                    }
                }
                if (s7_ == null) {
                    if ((isIntArray1()) && count7_ < (3)) {
                        // assert (s7_.objLibrary_.accepts(arg0Value));
                        s7_ = super.insert(new Int11Data(int11_cache));
                        s7_.objLibrary_ = s7_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.int11_cache = s7_;
                        this.state_0_ = state_0 = state_0 | 0b1000000 /* add-state_0 doInt1(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s7_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInt1(arg0Value, s7_.objLibrary_);
                }
            }
            {
                InteropLibrary int12_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isIntArray1())) {
                            int12_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b10 /* add-exclude doInt1(Object, InteropLibrary) */;
                            this.int11_cache = null;
                            state_0 = state_0 & 0xffffffbf /* remove-state_0 doInt1(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0b10000000 /* add-state_0 doInt1(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doInt1(arg0Value, int12_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b100)) == 0 /* is-not-exclude doInt2(Object, InteropLibrary) */) {
                int count9_ = 0;
                Int20Data s9_ = this.int20_cache;
                if (((state_0 & 0b100000000) != 0 /* is-state_0 doInt2(Object, InteropLibrary) */)) {
                    while (s9_ != null) {
                        if ((s9_.objLibrary_.accepts(arg0Value))) {
                            assert (isIntArray2());
                            break;
                        }
                        s9_ = s9_.next_;
                        count9_++;
                    }
                }
                if (s9_ == null) {
                    if ((isIntArray2()) && count9_ < (3)) {
                        // assert (s9_.objLibrary_.accepts(arg0Value));
                        s9_ = super.insert(new Int20Data(int20_cache));
                        s9_.objLibrary_ = s9_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.int20_cache = s9_;
                        this.state_0_ = state_0 = state_0 | 0b100000000 /* add-state_0 doInt2(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s9_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInt2(arg0Value, s9_.objLibrary_);
                }
            }
            {
                InteropLibrary int21_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isIntArray2())) {
                            int21_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b100 /* add-exclude doInt2(Object, InteropLibrary) */;
                            this.int20_cache = null;
                            state_0 = state_0 & 0xfffffeff /* remove-state_0 doInt2(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0b1000000000 /* add-state_0 doInt2(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doInt2(arg0Value, int21_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b1000)) == 0 /* is-not-exclude doInt3(Object, InteropLibrary) */) {
                int count11_ = 0;
                Int30Data s11_ = this.int30_cache;
                if (((state_0 & 0b10000000000) != 0 /* is-state_0 doInt3(Object, InteropLibrary) */)) {
                    while (s11_ != null) {
                        if ((s11_.objLibrary_.accepts(arg0Value))) {
                            assert (isIntArray3());
                            break;
                        }
                        s11_ = s11_.next_;
                        count11_++;
                    }
                }
                if (s11_ == null) {
                    if ((isIntArray3()) && count11_ < (3)) {
                        // assert (s11_.objLibrary_.accepts(arg0Value));
                        s11_ = super.insert(new Int30Data(int30_cache));
                        s11_.objLibrary_ = s11_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.int30_cache = s11_;
                        this.state_0_ = state_0 = state_0 | 0b10000000000 /* add-state_0 doInt3(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s11_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInt3(arg0Value, s11_.objLibrary_);
                }
            }
            {
                InteropLibrary int31_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isIntArray3())) {
                            int31_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b1000 /* add-exclude doInt3(Object, InteropLibrary) */;
                            this.int30_cache = null;
                            state_0 = state_0 & 0xfffffbff /* remove-state_0 doInt3(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0b100000000000 /* add-state_0 doInt3(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doInt3(arg0Value, int31_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b10000)) == 0 /* is-not-exclude doInt4(Object, InteropLibrary) */) {
                int count13_ = 0;
                Int40Data s13_ = this.int40_cache;
                if (((state_0 & 0b1000000000000) != 0 /* is-state_0 doInt4(Object, InteropLibrary) */)) {
                    while (s13_ != null) {
                        if ((s13_.objLibrary_.accepts(arg0Value))) {
                            assert (isIntArray4());
                            break;
                        }
                        s13_ = s13_.next_;
                        count13_++;
                    }
                }
                if (s13_ == null) {
                    if ((isIntArray4()) && count13_ < (3)) {
                        // assert (s13_.objLibrary_.accepts(arg0Value));
                        s13_ = super.insert(new Int40Data(int40_cache));
                        s13_.objLibrary_ = s13_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.int40_cache = s13_;
                        this.state_0_ = state_0 = state_0 | 0b1000000000000 /* add-state_0 doInt4(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s13_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInt4(arg0Value, s13_.objLibrary_);
                }
            }
            {
                InteropLibrary int41_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isIntArray4())) {
                            int41_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b10000 /* add-exclude doInt4(Object, InteropLibrary) */;
                            this.int40_cache = null;
                            state_0 = state_0 & 0xffffefff /* remove-state_0 doInt4(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0b10000000000000 /* add-state_0 doInt4(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doInt4(arg0Value, int41_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (arg0Value instanceof Value) {
                Value arg0Value_ = (Value) arg0Value;
                if ((isDouble())) {
                    this.state_0_ = state_0 = state_0 | 0b100000000000000 /* add-state_0 doDouble(Value) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doDouble(arg0Value_);
                }
                if ((isDoubleArray1())) {
                    this.state_0_ = state_0 = state_0 | 0b1000000000000000 /* add-state_0 doDouble1Value(Value) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doDouble1Value(arg0Value_);
                }
            }
            if (((exclude & 0b100000)) == 0 /* is-not-exclude doDouble(Object, InteropLibrary) */) {
                int count17_ = 0;
                Double10Data s17_ = this.double10_cache;
                if (((state_0 & 0x10000) != 0 /* is-state_0 doDouble(Object, InteropLibrary) */)) {
                    while (s17_ != null) {
                        if ((s17_.objLibrary_.accepts(arg0Value))) {
                            assert (isDouble());
                            break;
                        }
                        s17_ = s17_.next_;
                        count17_++;
                    }
                }
                if (s17_ == null) {
                    if ((isDouble()) && count17_ < (3)) {
                        // assert (s17_.objLibrary_.accepts(arg0Value));
                        s17_ = super.insert(new Double10Data(double10_cache));
                        s17_.objLibrary_ = s17_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.double10_cache = s17_;
                        this.state_0_ = state_0 = state_0 | 0x10000 /* add-state_0 doDouble(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s17_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doDouble(arg0Value, s17_.objLibrary_);
                }
            }
            {
                InteropLibrary double20_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isDouble())) {
                            double20_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b100000 /* add-exclude doDouble(Object, InteropLibrary) */;
                            this.double10_cache = null;
                            state_0 = state_0 & 0xfffeffff /* remove-state_0 doDouble(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0x20000 /* add-state_0 doDouble(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doDouble(arg0Value, double20_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b1000000)) == 0 /* is-not-exclude doDouble1(Object, InteropLibrary) */) {
                int count19_ = 0;
                Double11Data s19_ = this.double11_cache;
                if (((state_0 & 0x40000) != 0 /* is-state_0 doDouble1(Object, InteropLibrary) */)) {
                    while (s19_ != null) {
                        if ((s19_.objLibrary_.accepts(arg0Value))) {
                            assert (isDoubleArray1());
                            break;
                        }
                        s19_ = s19_.next_;
                        count19_++;
                    }
                }
                if (s19_ == null) {
                    if ((isDoubleArray1()) && count19_ < (3)) {
                        // assert (s19_.objLibrary_.accepts(arg0Value));
                        s19_ = super.insert(new Double11Data(double11_cache));
                        s19_.objLibrary_ = s19_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.double11_cache = s19_;
                        this.state_0_ = state_0 = state_0 | 0x40000 /* add-state_0 doDouble1(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s19_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doDouble1(arg0Value, s19_.objLibrary_);
                }
            }
            {
                InteropLibrary double12_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isDoubleArray1())) {
                            double12_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b1000000 /* add-exclude doDouble1(Object, InteropLibrary) */;
                            this.double11_cache = null;
                            state_0 = state_0 & 0xfffbffff /* remove-state_0 doDouble1(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0x80000 /* add-state_0 doDouble1(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doDouble1(arg0Value, double12_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b10000000)) == 0 /* is-not-exclude doDouble2(Object, InteropLibrary) */) {
                int count21_ = 0;
                Double21Data s21_ = this.double21_cache;
                if (((state_0 & 0x100000) != 0 /* is-state_0 doDouble2(Object, InteropLibrary) */)) {
                    while (s21_ != null) {
                        if ((s21_.objLibrary_.accepts(arg0Value))) {
                            assert (isDoubleArray2());
                            break;
                        }
                        s21_ = s21_.next_;
                        count21_++;
                    }
                }
                if (s21_ == null) {
                    if ((isDoubleArray2()) && count21_ < (3)) {
                        // assert (s21_.objLibrary_.accepts(arg0Value));
                        s21_ = super.insert(new Double21Data(double21_cache));
                        s21_.objLibrary_ = s21_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.double21_cache = s21_;
                        this.state_0_ = state_0 = state_0 | 0x100000 /* add-state_0 doDouble2(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s21_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doDouble2(arg0Value, s21_.objLibrary_);
                }
            }
            {
                InteropLibrary double22_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isDoubleArray2())) {
                            double22_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b10000000 /* add-exclude doDouble2(Object, InteropLibrary) */;
                            this.double21_cache = null;
                            state_0 = state_0 & 0xffefffff /* remove-state_0 doDouble2(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0x200000 /* add-state_0 doDouble2(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doDouble2(arg0Value, double22_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b100000000)) == 0 /* is-not-exclude doDouble3(Object, InteropLibrary) */) {
                int count23_ = 0;
                Double30Data s23_ = this.double30_cache;
                if (((state_0 & 0x400000) != 0 /* is-state_0 doDouble3(Object, InteropLibrary) */)) {
                    while (s23_ != null) {
                        if ((s23_.objLibrary_.accepts(arg0Value))) {
                            assert (isDoubleArray3());
                            break;
                        }
                        s23_ = s23_.next_;
                        count23_++;
                    }
                }
                if (s23_ == null) {
                    if ((isDoubleArray3()) && count23_ < (3)) {
                        // assert (s23_.objLibrary_.accepts(arg0Value));
                        s23_ = super.insert(new Double30Data(double30_cache));
                        s23_.objLibrary_ = s23_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.double30_cache = s23_;
                        this.state_0_ = state_0 = state_0 | 0x400000 /* add-state_0 doDouble3(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s23_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doDouble3(arg0Value, s23_.objLibrary_);
                }
            }
            {
                InteropLibrary double31_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isDoubleArray3())) {
                            double31_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b100000000 /* add-exclude doDouble3(Object, InteropLibrary) */;
                            this.double30_cache = null;
                            state_0 = state_0 & 0xffbfffff /* remove-state_0 doDouble3(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0x800000 /* add-state_0 doDouble3(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doDouble3(arg0Value, double31_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b1000000000)) == 0 /* is-not-exclude doDouble4(Object, InteropLibrary) */) {
                int count25_ = 0;
                Double40Data s25_ = this.double40_cache;
                if (((state_0 & 0x1000000) != 0 /* is-state_0 doDouble4(Object, InteropLibrary) */)) {
                    while (s25_ != null) {
                        if ((s25_.objLibrary_.accepts(arg0Value))) {
                            assert (isDoubleArray4());
                            break;
                        }
                        s25_ = s25_.next_;
                        count25_++;
                    }
                }
                if (s25_ == null) {
                    if ((isDoubleArray4()) && count25_ < (3)) {
                        // assert (s25_.objLibrary_.accepts(arg0Value));
                        s25_ = super.insert(new Double40Data(double40_cache));
                        s25_.objLibrary_ = s25_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.double40_cache = s25_;
                        this.state_0_ = state_0 = state_0 | 0x1000000 /* add-state_0 doDouble4(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s25_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doDouble4(arg0Value, s25_.objLibrary_);
                }
            }
            {
                InteropLibrary double41_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isDoubleArray4())) {
                            double41_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b1000000000 /* add-exclude doDouble4(Object, InteropLibrary) */;
                            this.double40_cache = null;
                            state_0 = state_0 & 0xfeffffff /* remove-state_0 doDouble4(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0x2000000 /* add-state_0 doDouble4(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doDouble4(arg0Value, double41_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (arg0Value instanceof Value) {
                Value arg0Value_ = (Value) arg0Value;
                if ((isBool())) {
                    this.state_0_ = state_0 = state_0 | 0x4000000 /* add-state_0 doBoolValue(Value) */;
                    this.state_1_ = state_1;
                    lock.unlock();
                    hasLock = false;
                    return doBoolValue(arg0Value_);
                }
            }
            if (((exclude & 0b10000000000)) == 0 /* is-not-exclude doBool(Object, InteropLibrary) */) {
                int count28_ = 0;
                Bool0Data s28_ = this.bool0_cache;
                if (((state_0 & 0x8000000) != 0 /* is-state_0 doBool(Object, InteropLibrary) */)) {
                    while (s28_ != null) {
                        if ((s28_.objLibrary_.accepts(arg0Value))) {
                            assert (isBool());
                            break;
                        }
                        s28_ = s28_.next_;
                        count28_++;
                    }
                }
                if (s28_ == null) {
                    if ((isBool()) && count28_ < (3)) {
                        // assert (s28_.objLibrary_.accepts(arg0Value));
                        s28_ = super.insert(new Bool0Data(bool0_cache));
                        s28_.objLibrary_ = s28_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.bool0_cache = s28_;
                        this.state_0_ = state_0 = state_0 | 0x8000000 /* add-state_0 doBool(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s28_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBool(arg0Value, s28_.objLibrary_);
                }
            }
            {
                InteropLibrary bool10_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isBool())) {
                            bool10_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b10000000000 /* add-exclude doBool(Object, InteropLibrary) */;
                            this.bool0_cache = null;
                            state_0 = state_0 & 0xf7ffffff /* remove-state_0 doBool(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0x10000000 /* add-state_0 doBool(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doBool(arg0Value, bool10_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b100000000000)) == 0 /* is-not-exclude doBool1(Object, InteropLibrary) */) {
                int count30_ = 0;
                Bool11Data s30_ = this.bool11_cache;
                if (((state_0 & 0x20000000) != 0 /* is-state_0 doBool1(Object, InteropLibrary) */)) {
                    while (s30_ != null) {
                        if ((s30_.objLibrary_.accepts(arg0Value))) {
                            assert (isBoolArray1());
                            break;
                        }
                        s30_ = s30_.next_;
                        count30_++;
                    }
                }
                if (s30_ == null) {
                    if ((isBoolArray1()) && count30_ < (3)) {
                        // assert (s30_.objLibrary_.accepts(arg0Value));
                        s30_ = super.insert(new Bool11Data(bool11_cache));
                        s30_.objLibrary_ = s30_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.bool11_cache = s30_;
                        this.state_0_ = state_0 = state_0 | 0x20000000 /* add-state_0 doBool1(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s30_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBool1(arg0Value, s30_.objLibrary_);
                }
            }
            {
                InteropLibrary bool12_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isBoolArray1())) {
                            bool12_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b100000000000 /* add-exclude doBool1(Object, InteropLibrary) */;
                            this.bool11_cache = null;
                            state_0 = state_0 & 0xdfffffff /* remove-state_0 doBool1(Object, InteropLibrary) */;
                            this.state_0_ = state_0 = state_0 | 0x40000000 /* add-state_0 doBool1(Object, InteropLibrary) */;
                            this.state_1_ = state_1;
                            lock.unlock();
                            hasLock = false;
                            return doBool1(arg0Value, bool12_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b1000000000000)) == 0 /* is-not-exclude doBool2(Object, InteropLibrary) */) {
                int count32_ = 0;
                Bool20Data s32_ = this.bool20_cache;
                if (((state_0 & 0x80000000) != 0 /* is-state_0 doBool2(Object, InteropLibrary) */)) {
                    while (s32_ != null) {
                        if ((s32_.objLibrary_.accepts(arg0Value))) {
                            assert (isBoolArray2());
                            break;
                        }
                        s32_ = s32_.next_;
                        count32_++;
                    }
                }
                if (s32_ == null) {
                    if ((isBoolArray2()) && count32_ < (3)) {
                        // assert (s32_.objLibrary_.accepts(arg0Value));
                        s32_ = super.insert(new Bool20Data(bool20_cache));
                        s32_.objLibrary_ = s32_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.bool20_cache = s32_;
                        this.state_0_ = state_0 = state_0 | 0x80000000 /* add-state_0 doBool2(Object, InteropLibrary) */;
                        this.state_1_ = state_1;
                    }
                }
                if (s32_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBool2(arg0Value, s32_.objLibrary_);
                }
            }
            {
                InteropLibrary bool21_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isBoolArray2())) {
                            bool21_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b1000000000000 /* add-exclude doBool2(Object, InteropLibrary) */;
                            this.bool20_cache = null;
                            state_0 = state_0 & 0x7fffffff /* remove-state_0 doBool2(Object, InteropLibrary) */;
                            this.state_0_ = state_0;
                            this.state_1_ = state_1 = state_1 | 0b1 /* add-state_1 doBool2(Object, InteropLibrary) */;
                            lock.unlock();
                            hasLock = false;
                            return doBool2(arg0Value, bool21_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b10000000000000)) == 0 /* is-not-exclude doBool3(Object, InteropLibrary) */) {
                int count34_ = 0;
                Bool30Data s34_ = this.bool30_cache;
                if (((state_1 & 0b10) != 0 /* is-state_1 doBool3(Object, InteropLibrary) */)) {
                    while (s34_ != null) {
                        if ((s34_.objLibrary_.accepts(arg0Value))) {
                            assert (isBoolArray3());
                            break;
                        }
                        s34_ = s34_.next_;
                        count34_++;
                    }
                }
                if (s34_ == null) {
                    if ((isBoolArray3()) && count34_ < (3)) {
                        // assert (s34_.objLibrary_.accepts(arg0Value));
                        s34_ = super.insert(new Bool30Data(bool30_cache));
                        s34_.objLibrary_ = s34_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.bool30_cache = s34_;
                        this.state_0_ = state_0;
                        this.state_1_ = state_1 = state_1 | 0b10 /* add-state_1 doBool3(Object, InteropLibrary) */;
                    }
                }
                if (s34_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBool3(arg0Value, s34_.objLibrary_);
                }
            }
            {
                InteropLibrary bool31_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isBoolArray3())) {
                            bool31_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b10000000000000 /* add-exclude doBool3(Object, InteropLibrary) */;
                            this.bool30_cache = null;
                            state_1 = state_1 & 0xfffffffd /* remove-state_1 doBool3(Object, InteropLibrary) */;
                            this.state_0_ = state_0;
                            this.state_1_ = state_1 = state_1 | 0b100 /* add-state_1 doBool3(Object, InteropLibrary) */;
                            lock.unlock();
                            hasLock = false;
                            return doBool3(arg0Value, bool31_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b100000000000000)) == 0 /* is-not-exclude doBool4(Object, InteropLibrary) */) {
                int count36_ = 0;
                Bool40Data s36_ = this.bool40_cache;
                if (((state_1 & 0b1000) != 0 /* is-state_1 doBool4(Object, InteropLibrary) */)) {
                    while (s36_ != null) {
                        if ((s36_.objLibrary_.accepts(arg0Value))) {
                            assert (isBoolArray4());
                            break;
                        }
                        s36_ = s36_.next_;
                        count36_++;
                    }
                }
                if (s36_ == null) {
                    if ((isBoolArray4()) && count36_ < (3)) {
                        // assert (s36_.objLibrary_.accepts(arg0Value));
                        s36_ = super.insert(new Bool40Data(bool40_cache));
                        s36_.objLibrary_ = s36_.insertAccessor((INTEROP_LIBRARY_.create(arg0Value)));
                        this.bool40_cache = s36_;
                        this.state_0_ = state_0;
                        this.state_1_ = state_1 = state_1 | 0b1000 /* add-state_1 doBool4(Object, InteropLibrary) */;
                    }
                }
                if (s36_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doBool4(arg0Value, s36_.objLibrary_);
                }
            }
            {
                InteropLibrary bool41_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        if ((isBoolArray4())) {
                            bool41_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                            this.exclude_ = exclude = exclude | 0b100000000000000 /* add-exclude doBool4(Object, InteropLibrary) */;
                            this.bool40_cache = null;
                            state_1 = state_1 & 0xfffffff7 /* remove-state_1 doBool4(Object, InteropLibrary) */;
                            this.state_0_ = state_0;
                            this.state_1_ = state_1 = state_1 | 0b10000 /* add-state_1 doBool4(Object, InteropLibrary) */;
                            lock.unlock();
                            hasLock = false;
                            return doBool4(arg0Value, bool41_objLibrary__);
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
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
        int state_0 = state_0_;
        int state_1 = state_1_;
        if (state_0 == 0 && state_1 == 0) {
            return NodeCost.UNINITIALIZED;
        } else {
            int counter = 0;
            counter += Integer.bitCount(state_0);
            counter += Integer.bitCount(state_1);
            if (counter == 1) {
                HostObjectData s2_ = this.hostObject_cache;
                Int0Data s5_ = this.int0_cache;
                Int11Data s7_ = this.int11_cache;
                Int20Data s9_ = this.int20_cache;
                Int30Data s11_ = this.int30_cache;
                Int40Data s13_ = this.int40_cache;
                Double10Data s17_ = this.double10_cache;
                Double11Data s19_ = this.double11_cache;
                Double21Data s21_ = this.double21_cache;
                Double30Data s23_ = this.double30_cache;
                Double40Data s25_ = this.double40_cache;
                Bool0Data s28_ = this.bool0_cache;
                Bool11Data s30_ = this.bool11_cache;
                Bool20Data s32_ = this.bool20_cache;
                Bool30Data s34_ = this.bool30_cache;
                Bool40Data s36_ = this.bool40_cache;
                if ((s2_ == null || s2_.next_ == null) && (s5_ == null || s5_.next_ == null) && (s7_ == null || s7_.next_ == null) && (s9_ == null || s9_.next_ == null) && (s11_ == null || s11_.next_ == null) && (s13_ == null || s13_.next_ == null) && (s17_ == null || s17_.next_ == null) && (s19_ == null || s19_.next_ == null) && (s21_ == null || s21_.next_ == null) && (s23_ == null || s23_.next_ == null) && (s25_ == null || s25_.next_ == null) && (s28_ == null || s28_.next_ == null) && (s30_ == null || s30_.next_ == null) && (s32_ == null || s32_.next_ == null) && (s34_ == null || s34_.next_ == null) && (s36_ == null || s36_.next_ == null)) {
                    return NodeCost.MONOMORPHIC;
                }
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static BoxValueNode create(Class<?> type) {
        return new BoxValueNodeGen(type);
    }

    @GeneratedBy(BoxValueNode.class)
    private static final class HostObjectData {

        @CompilationFinal HostObjectData next_;
        @CompilationFinal Env env_;

        HostObjectData(HostObjectData next_) {
            this.next_ = next_;
        }

    }
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
    private static final class Double10Data extends Node {

        @Child Double10Data next_;
        @Child InteropLibrary objLibrary_;

        Double10Data(Double10Data next_) {
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
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
    private static final class Double21Data extends Node {

        @Child Double21Data next_;
        @Child InteropLibrary objLibrary_;

        Double21Data(Double21Data next_) {
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
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
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
    @GeneratedBy(BoxValueNode.class)
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
}
