import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class HelloTest extends AnyFlatSpec with ChiselScalatestTester {

  "Node" should "compare, forward and hold" in {
    test(new Node()) { dut =>
      println("Testing Node basics")
      
      // Initialize all signals
      dut.io.front.keyIn.poke(5.U)
      dut.io.front.valueIn.poke(42.U)
      dut.io.front.cmdIn.poke(0.U) // Testing IDLE
      dut.io.back.keyIn.poke(0.U)
      dut.io.back.valueIn.poke(0.U)
      dut.clock.step(1)

      dut.io.back.keyOut.expect(5.U)
      dut.io.back.valueOut.expect(42.U)
      dut.io.back.cmdOut.expect(0.U)



      dut.io.front.keyIn.poke(3.U)
      dut.io.front.valueIn.poke(42.U)
      dut.io.front.cmdIn.poke(1.U) // Testing PUSH
      dut.io.back.keyIn.poke(0.U)
      dut.io.back.valueIn.poke(0.U)
      dut.clock.step(1)

      dut.io.back.keyOut.expect(-1.U)
      dut.io.back.valueOut.expect(0.U)
      dut.io.back.cmdOut.expect(1.U)



      dut.io.front.keyIn.poke(5.U)
      dut.io.front.valueIn.poke(42.U)
      dut.io.front.cmdIn.poke(2.U) // Testing POP
      dut.io.back.keyIn.poke(0.U)
      dut.io.back.valueIn.poke(0.U)
      dut.clock.step(1)

      dut.io.back.keyOut.expect(0.U)
      dut.io.back.valueOut.expect(42.U)
      dut.io.back.cmdOut.expect(2.U)



      dut.io.front.keyIn.poke(5.U)
      dut.io.front.valueIn.poke(42.U)
      dut.io.front.cmdIn.poke(3.U) // Testing PUSH/POP
      dut.io.back.keyIn.poke(0.U)
      dut.io.back.valueIn.poke(0.U)
      dut.clock.step(1)

      dut.io.back.keyOut.expect(0.U)
      dut.io.back.valueOut.expect(42.U)
      dut.io.back.cmdOut.expect(3.U)
    }
  }
}

