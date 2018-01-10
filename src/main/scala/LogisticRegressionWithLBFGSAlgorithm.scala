package org.template.classification

import org.apache.prediction.controller.P2LAlgorithm
import org.apache.prediction.controller.Params

import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS
import org.apache.spark.mllib.classification.LogisticRegressionModel
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.SparkContext

import grizzled.slf4j.Logger

case class AlgorithmParams(
  maxIter: Int,
  regParam: Double,
  fitIntercept: Boolean,
  categoricalPrediction: Boolean
) extends Params

// extends P2LAlgorithm because the MLlib's LogisticRegressionModel doesn't contain RDD.
class LogisticRegressionWithLBFGSAlgorithm(val ap: AlgorithmParams)
  extends P2LAlgorithm[PreparedData, LogisticRegressionModel, Query, PredictedResult] {

  @transient lazy val logger = Logger[this.type]

  def train(sc: SparkContext, data: PreparedData): LogisticRegressionModel = {
    // MLLib cannot handle empty training data.
    require(!data.labeledPoints.take(1).isEmpty,
      s"RDD[labeldPoints] in PreparedData cannot be empty." +
      " Please check if DataSource generates TrainingData" +
      " and Preprator generates PreparedData correctly.")
   
    val lr = new LogisticRegressionWithLBFGS().setIntercept(ap.fitIntercept)
    lr.optimizer.setNumIterations(ap.maxIter).setRegParam(ap.regParam)
  
    lr.run(data.labeledPoints)
  }

  def predict(model: LogisticRegressionModel, query: Query): PredictedResult = {
    if (!ap.categoricalPrediction) model.clearThreshold()
    val label = model.predict(Vectors.dense(query.features))
    new PredictedResult(label)
  }

}
