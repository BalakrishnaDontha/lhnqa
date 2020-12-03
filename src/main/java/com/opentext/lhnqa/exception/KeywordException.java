package com.opentext.lhnqa.exception;

/**
 * General purpose exception for exceptions that happen in keywords.
 * 
 * Use this exception to indicate that your keyword failed.
 */
public class KeywordException
    extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  /**
   * Construct a new {@link KeywordException} with message and cause.
   * 
   * @param aMessage the message
   * @param aCause the cause
   */
  public KeywordException(String aMessage, Throwable aCause)
  {
    super(aMessage, aCause);
  }

  /**
   * Construct a new {@link KeywordException} with a message.
   * 
   * @param aMessage the message
   */
  public KeywordException(String aMessage)
  {
    super(aMessage);
  }

  /**
   * Construct a new {@link KeywordException} with a cause.
   * 
   * @param aCause the cause
   */
  public KeywordException(Throwable aCause)
  {
    super(aCause);
  }

}
