package fr.cea.nabla.aspects;

import org.gemoc.instrument.IContextWrapper;
import org.gemoc.instrument.InstrumentableAspect;

import fr.cea.nabla.ir.interpreter.Context;
import fr.cea.nabla.ir.ir.Function;
import fr.cea.nabla.ir.ir.Job;

public aspect NablaAspect extends InstrumentableAspect<Context> {

	pointcut interpreteJob(Job job, Context context) :
		call(public void fr.cea.nabla.ir.interpreter.JobInterpreter.interprete(Job, Context)) &&
				args(job, context);

	pointcut interpreteFunction(Function function, Context context) :
		call(public void fr.cea.nabla.ir.interpreter.ExpressionInterpreter.interpreteFunctionCall(Function, Context)) &&
				args(function, context);

	after(Job job, Context context) : interpreteJob(job, context) {
		notifyAfter(job.getName(), null, context);
	}

	before(Job job, Context context) : interpreteJob(job, context) {
		notifyBefore(job.getName(), context);
	}

	after(Function function, Context context) returning (Object result) : interpreteFunction(function, context) {
		notifyAfter(function.getName(), result, context);
	}

	before(Function function, Context context) : interpreteFunction(function, context) {
		notifyBefore(function.getName(), context);
	}

	@Override
	protected IContextWrapper getContextWrapper(Context context) {
		return new NablaContextWrapper(context);
	}
}
