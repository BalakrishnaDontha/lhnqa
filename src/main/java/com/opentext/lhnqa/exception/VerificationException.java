package com.opentext.lhnqa.exception;

/**
 * Exception indicates that a verification keyword failed.
 * 
 * @author ulr
 */
public class VerificationException
    extends RuntimeException
{
  private static final long serialVersionUID = 2454128399123812693L;

  /**
   * Construct a new {@link VerificationException} with message and cause.
   * 
   * @param aMessage the message
   * @param aCause the cause
   */
  public VerificationException(String aMessage, Throwable aCause)
  {
    super(aMessage, aCause);
  }

  /**
   * Construct a new {@link KeywordException} with a message.
   * 
   * @param aMessage the message
   */
  public VerificationException(String aMessage)
  {
    super(aMessage);
  }

  /**
   * Construct a new {@link KeywordException} with a cause.
   * 
   * @param aCause the cause
   */
  public VerificationException(Throwable aCause)
  {
    super(aCause);
  }

}
