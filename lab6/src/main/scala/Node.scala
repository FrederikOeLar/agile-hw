import chisel3._

class Node(keySize: Int = 8, payLoadSize: Int = 32) extends Module {
  val io = IO(new Bundle {
    val front = IO(new Bundle {
      val keyIn = Input(UInt(keySize.W))
      val valueIn = Input(UInt(payLoadSize.W))
      val cmdIn = Input(UInt(2.W))

      val keyOut = Output(UInt(keySize.W))
      val valueOut = Output(UInt(payLoadSize.W))
    })

    val back = IO(new Bundle {
      val keyIn = Input(UInt(keySize.W))
      val valueIn = Input(UInt(payLoadSize.W))

      val keyOut = Output(UInt(keySize.W))
      val valueOut = Output(UInt(payLoadSize.W))
      val cmdOut = Output(UInt(2.W))
    })
  })

  // Insane code here
  val key = RegInit(~0.U(keySize.W)) // Init to highest possible value (least important)
  val value = RegInit(0.U(payLoadSize.W))

}

object Node extends App {
  println("Generating Verilog for a Node!")
  emitVerilog(new Node())
}
