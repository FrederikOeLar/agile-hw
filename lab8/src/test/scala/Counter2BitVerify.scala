import chisel3._
import chiseltest._
import chiseltest.formal._
import org.scalatest.flatspec.AnyFlatSpec

class Counter2BitVerify extends AnyFlatSpec with ChiselScalatestTester with Formal {

  "Counter2Bit" should "work with verification" in {
    verify(new Counter2Bit, Seq(BoundedCheck (5), WriteVcdAnnotation))
  }
}