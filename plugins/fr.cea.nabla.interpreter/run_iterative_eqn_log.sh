#!/usr/bin/env bash

~/Téléchargements/graalvm-ce-java11-20.0.0/bin/nabla ~/git/NabLab-2/NabLab/plugins/fr.cea.nabla.ui/examples/NablaExamples/src/iterativeheatequation/IterativeHeatEquation.nabla ~/git/NabLab-2/NabLab/plugins/fr.cea.nabla.ui/examples/NablaExamples/src/iterativeheatequation/IterativeHeatEquation.nablagen --nabla-logger=true --nabla-logger.format=true --nabla-logger.jobs=UpdateU --nabla-logger.variables=t_nplus1,\ u_nplus1,\ n --nabla-logger.range=3 --nabla-logger.toPrint=At\ n={2},\ t={0},\ U={1} "$@"
