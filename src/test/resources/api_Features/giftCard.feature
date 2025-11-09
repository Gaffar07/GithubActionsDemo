@api @giftcardapi
Feature: Giftcard scenarios

  @giftcard1
  Scenario: gift_card_1-Check giftcard balance
    Given user access token is generated
    When a Kount session is created

