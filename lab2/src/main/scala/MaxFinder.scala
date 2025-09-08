
import chisel3._
import chisel3.util._

/**
  * Find the maximum value in a Vec using treeReduce
  * @param n number of elements
  */
class MaxFinder(val n: Int, val width: Int) extends Module {
  val io = IO(new Bundle {
    val in = Input(Vec(n, UInt(width.W)))

    val max = Output(UInt(width.W))
    val maxIdx = Output(UInt(log2Ceil(n).W))
  })

  val maxZip = io.in.zipWithIndex.map((x) => (x._1, x._2.U))
                  .reduce((x, y) => (Mux(x._1 > y._1, x._1, y._1), Mux(x._1 > y._1, x._2, y._2)))

  io.max := maxZip._1
  io.maxIdx := maxZip._2
}
