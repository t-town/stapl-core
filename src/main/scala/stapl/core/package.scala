package stapl

import scala.language.implicitConversions

package object core {
  
  implicit def boolean2Value(boolean: Boolean): ConcreteValue = new BoolImpl(boolean)
  
  implicit def int2Value(int: Int): ConcreteValue = new NumberImpl(Left(int))
  
  implicit def double2Value(double: Double): ConcreteValue = new NumberImpl(Right(double))
  
  implicit def long2Value(long: Long): ConcreteValue = new NumberImpl(Left(long))
  
  implicit def string2Value(string: String): ConcreteValue = new StringImpl(string)
  
  
  implicit def stringSeq2Value(seq: Seq[String]): ConcreteValue = new StringSeqImpl(seq)
  
  implicit def intSeq2Value(seq: Seq[Int]): ConcreteValue = new IntSeqImpl(seq)
  
  implicit def doubleSeq2Value(seq: Seq[Double]): ConcreteValue = new DoubleSeqImpl(seq)
  
  implicit def longSeq2Value(seq: Seq[Long]): ConcreteValue = new LongSeqImpl(seq)
  
  implicit def dateTimeSeq2Value(seq: Seq[DateTimeImpl]): ConcreteValue = new DateTimeSeqImpl(seq)
  
  implicit def timeSeq2Value(seq: Seq[TimeImpl]): ConcreteValue = new TimeSeqImpl(seq)
  
  implicit def daySeq2Value(seq: Seq[DayImpl]): ConcreteValue = new DaySeqImpl(seq)
  
  implicit def dateTimeDurSeq2Value(seq: Seq[DateTimeDurationImpl]): ConcreteValue = new DateTimeDurSeqImpl(seq)
  
  implicit def timeDurSeq2Value(seq: Seq[TimeDurationImpl]): ConcreteValue = new TimeDurSeqImpl(seq)
  
  implicit def dayDurSeq2Value(seq: Seq[DayDurationImpl]): ConcreteValue = new DayDurSeqImpl(seq)
  
  
  implicit def boolAttributeToExpression(attribute: Attribute): Expression = attribute match {
    case x@SimpleAttribute(_,_,Bool) => BoolExpression(x)
    case SimpleAttribute(_,_,aType) => throw new TypeCheckException(aType, Bool)
    case _ => throw new IllegalArgumentException("Found a list, but expected a Bool.")
  }
  
  implicit def boolean2Expression(bool: Boolean): Expression = if(bool) AlwaysTrue else AlwaysFalse
  
  implicit def int2DurationBuilder(int: Int) = new DurationBuilder(int)
  
  
  def abs(value: Value): Operation = AbsoluteValue(value)
  
  
  def defaultPermit(id: String): Policy = 
    new Policy(id)(
	  target = AlwaysTrue,
	  effect = Permit,
	  condition = AlwaysTrue
	)

  def defaultDeny(id: String): Policy = 
    new Policy(id)(
	  target = AlwaysTrue,
	  effect = Deny,
	  condition = AlwaysTrue
	)
}