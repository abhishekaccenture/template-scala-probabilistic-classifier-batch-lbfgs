package org.template.classification

import org.apache.prediction.controller.IEngineFactory
import org.apache.prediction.controller.Engine

class Query(
  val features: Array[Double]
) extends Serializable

class PredictedResult(
  val label: Double
) extends Serializable

class ActualResult(
  val label: Double
) extends Serializable

object ClassificationEngine extends IEngineFactory {
  def apply() = {
    new Engine(
      classOf[DataSource],
      classOf[Preparator],
      Map("logisticLBFGS" -> classOf[LogisticRegressionWithLBFGSAlgorithm]),
      classOf[Serving])
  }
}
