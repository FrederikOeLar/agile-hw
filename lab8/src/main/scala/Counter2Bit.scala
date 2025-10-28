import chisel3._
import chisel3.util._
import chiseltest.formal._

class Counter2Bit extends Module {
  val io = IO(new Bundle {
    val out = Output(UInt(2.W))
  })
  val count = RegInit(0.U(2.W))
  count := count + 1.U
  io.out := count

  // Formal properties
  assert(count <= 3.U, "Counter should not exceed 3")
  when(count =/= 0.U){
    assert(count === past(count) + 1.U, "Counter should add 1 every cycle")
  } .elsewhen(count === 0.U){
    assert(count < past(count), "Overflow should work")
  }

  //assert(count =/= 3.U, "Assertion should fail when BoundedCheck > 3")
}