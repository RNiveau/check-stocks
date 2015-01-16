package net.blog.dev.services.domain.quote;

import net.blog.dev.services.jackson.deserializer.CleanFloatDeserializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.io.Serializable;

/**
 * Created by romainn on 16/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2096753063711776090L;
    private String symbol;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float Ask;
    @JsonProperty
    private Long AverageDailyVolume;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float Bid;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float AskRealtime;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float BidRealtime;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float BookValue;
    @JsonProperty
    private String Change_PercentChange;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float Change;
    @JsonProperty
    private String Commission;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float ChangeRealtime;
    @JsonProperty
    private String AfterHoursChangeRealtime;
    @JsonProperty
    private String DividendShare;
    @JsonProperty
    private String LastTradeDate;
    @JsonProperty
    private String TradeDate;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float EarningsShare;
    @JsonProperty
    private String ErrorIndicationreturnedforsymbolchangedinvalid;
    @JsonProperty
    private String EPSEstimateCurrentYear;
    @JsonProperty
    private String EPSEstimateNextYear;
    @JsonProperty
    private String EPSEstimateNextQuarter;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float DaysLow;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float DaysHigh;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float YearLow;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float YearHigh;
    @JsonProperty
    private String HoldingsGainPercent;
    @JsonProperty
    private String AnnualizedGain;
    @JsonProperty
    private String HoldingsGain;
    @JsonProperty
    private String HoldingsGainPercentRealtime;
    @JsonProperty
    private String HoldingsGainRealtime;
    @JsonProperty
    private String MoreInfo;
    @JsonProperty
    private String OrderBookRealtime;
    @JsonProperty
    private String MarketCapitalization;
    @JsonProperty
    private String MarketCapRealtime;
    @JsonProperty
    private String EBITDA;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float ChangeFromYearLow;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float PercentChangeFromYearLow;
    @JsonProperty
    private String LastTradeRealtimeWithTime;
    @JsonProperty
    private String ChangePercentRealtime;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float ChangeFromYearHigh;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float PercebtChangeFromYearHigh;
    @JsonProperty
    private String LastTradeWithTime;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float LastTradePriceOnly;
    @JsonProperty
    private String HighLimit;
    @JsonProperty
    private String LowLimit;
    @JsonProperty
    private String DaysRange;
    @JsonProperty
    private String DaysRangeRealtime;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float FiftydayMovingAverage;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float TwoHundreddayMovingAverage;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float ChangeFromTwoHundreddayMovingAverage;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float PercentChangeFromTwoHundreddayMovingAverage;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float ChangeFromFiftydayMovingAverage;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float PercentChangeFromFiftydayMovingAverage;
    @JsonProperty
    private String Name;
    @JsonProperty
    private String Notes;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float Open;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float PreviousClose;
    @JsonProperty
    private String PricePaid;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float ChangeinPercent;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float PriceSales;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float PriceBook;
    @JsonProperty
    private String ExDividendDate;
    @JsonProperty
    private String PERatio;
    @JsonProperty
    private String DividendPayDate;
    @JsonProperty
    private String PERatioRealtime;
    @JsonProperty
    private String PEGRatio;
    @JsonProperty
    private String PriceEPSEstimateCurrentYear;
    @JsonProperty
    private String PriceEPSEstimateNextYear;
    @JsonProperty
    private String SharesOwned;
    @JsonProperty
    private String ShortRatio;
    @JsonProperty
    private String LastTradeTime;
    @JsonProperty
    private String TickerTrend;
    @JsonProperty
    private String OneyrTargetPrice;
    @JsonProperty
    private Long Volume;
    @JsonProperty
    private String HoldingsValue;
    @JsonProperty
    private String HoldingsValueRealtime;
    @JsonProperty
    private String YearRange;
    @JsonProperty
    private String DaysValueChange;
    @JsonProperty
    private String DaysValueChangeRealtime;
    @JsonProperty
    private String StockExchange;
    @JsonProperty
    private String DividendYield;
    @JsonProperty
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float PercentChange;

    /**
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param symbol
     *            the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @return the ask
     */
    public Float getAsk() {
        return Ask;
    }

    /**
     * @param ask
     *            the ask to set
     */
    public void setAsk(Float ask) {
        Ask = ask;
    }

    /**
     * @return the averageDailyVolume
     */
    public Long getAverageDailyVolume() {
        return AverageDailyVolume;
    }

    /**
     * @param averageDailyVolume
     *            the averageDailyVolume to set
     */
    public void setAverageDailyVolume(Long averageDailyVolume) {
        AverageDailyVolume = averageDailyVolume;
    }

    /**
     * @return the bid
     */
    public Float getBid() {
        return Bid;
    }

    /**
     * @param bid
     *            the bid to set
     */
    public void setBid(Float bid) {
        Bid = bid;
    }

    /**
     * @return the askRealtime
     */
    public Float getAskRealtime() {
        return AskRealtime;
    }

    /**
     * @param askRealtime
     *            the askRealtime to set
     */
    public void setAskRealtime(Float askRealtime) {
        AskRealtime = askRealtime;
    }

    /**
     * @return the bidRealtime
     */
    public Float getBidRealtime() {
        return BidRealtime;
    }

    /**
     * @param bidRealtime
     *            the bidRealtime to set
     */
    public void setBidRealtime(Float bidRealtime) {
        BidRealtime = bidRealtime;
    }

    /**
     * @return the bookValue
     */
    public Float getBookValue() {
        return BookValue;
    }

    /**
     * @param bookValue
     *            the bookValue to set
     */
    public void setBookValue(Float bookValue) {
        BookValue = bookValue;
    }

    /**
     * @return the change_PercentChange
     */
    public String getChange_PercentChange() {
        return Change_PercentChange;
    }

    /**
     * @param change_PercentChange
     *            the change_PercentChange to set
     */
    public void setChange_PercentChange(String change_PercentChange) {
        Change_PercentChange = change_PercentChange;
    }

    /**
     * @return the change
     */
    public Float getChange() {
        return Change;
    }

    /**
     * @param change
     *            the change to set
     */
    public void setChange(Float change) {
        Change = change;
    }

    /**
     * @return the commission
     */
    public String getCommission() {
        return Commission;
    }

    /**
     * @param commission
     *            the commission to set
     */
    public void setCommission(String commission) {
        Commission = commission;
    }

    /**
     * @return the changeRealtime
     */
    public Float getChangeRealtime() {
        return ChangeRealtime;
    }

    /**
     * @param changeRealtime
     *            the changeRealtime to set
     */
    public void setChangeRealtime(Float changeRealtime) {
        ChangeRealtime = changeRealtime;
    }

    /**
     * @return the afterHoursChangeRealtime
     */
    public String getAfterHoursChangeRealtime() {
        return AfterHoursChangeRealtime;
    }

    /**
     * @param afterHoursChangeRealtime
     *            the afterHoursChangeRealtime to set
     */
    public void setAfterHoursChangeRealtime(String afterHoursChangeRealtime) {
        AfterHoursChangeRealtime = afterHoursChangeRealtime;
    }

    /**
     * @return the dividendShare
     */
    public String getDividendShare() {
        return DividendShare;
    }

    /**
     * @param dividendShare
     *            the dividendShare to set
     */
    public void setDividendShare(String dividendShare) {
        DividendShare = dividendShare;
    }

    /**
     * @return the lastTradeDate
     */
    public String getLastTradeDate() {
        return LastTradeDate;
    }

    /**
     * @param lastTradeDate
     *            the lastTradeDate to set
     */
    public void setLastTradeDate(String lastTradeDate) {
        LastTradeDate = lastTradeDate;
    }

    /**
     * @return the tradeDate
     */
    public String getTradeDate() {
        return TradeDate;
    }

    /**
     * @param tradeDate
     *            the tradeDate to set
     */
    public void setTradeDate(String tradeDate) {
        TradeDate = tradeDate;
    }

    /**
     * @return the earningsShare
     */
    public Float getEarningsShare() {
        return EarningsShare;
    }

    /**
     * @param earningsShare
     *            the earningsShare to set
     */
    public void setEarningsShare(Float earningsShare) {
        EarningsShare = earningsShare;
    }

    /**
     * @return the errorIndicationreturnedforsymbolchangedinvalid
     */
    public String getErrorIndicationreturnedforsymbolchangedinvalid() {
        return ErrorIndicationreturnedforsymbolchangedinvalid;
    }

    /**
     * @param errorIndicationreturnedforsymbolchangedinvalid
     *            the errorIndicationreturnedforsymbolchangedinvalid to set
     */
    public void setErrorIndicationreturnedforsymbolchangedinvalid(
            String errorIndicationreturnedforsymbolchangedinvalid) {
        ErrorIndicationreturnedforsymbolchangedinvalid = errorIndicationreturnedforsymbolchangedinvalid;
    }

    /**
     * @return the ePSEstimateCurrentYear
     */
    public String getEPSEstimateCurrentYear() {
        return EPSEstimateCurrentYear;
    }

    /**
     * @param ePSEstimateCurrentYear
     *            the ePSEstimateCurrentYear to set
     */
    public void setEPSEstimateCurrentYear(String ePSEstimateCurrentYear) {
        EPSEstimateCurrentYear = ePSEstimateCurrentYear;
    }

    /**
     * @return the ePSEstimateNextYear
     */
    public String getEPSEstimateNextYear() {
        return EPSEstimateNextYear;
    }

    /**
     * @param ePSEstimateNextYear
     *            the ePSEstimateNextYear to set
     */
    public void setEPSEstimateNextYear(String ePSEstimateNextYear) {
        EPSEstimateNextYear = ePSEstimateNextYear;
    }

    /**
     * @return the ePSEstimateNextQuarter
     */
    public String getEPSEstimateNextQuarter() {
        return EPSEstimateNextQuarter;
    }

    /**
     * @param ePSEstimateNextQuarter
     *            the ePSEstimateNextQuarter to set
     */
    public void setEPSEstimateNextQuarter(String ePSEstimateNextQuarter) {
        EPSEstimateNextQuarter = ePSEstimateNextQuarter;
    }

    /**
     * @return the daysLow
     */
    public Float getDaysLow() {
        return DaysLow;
    }

    /**
     * @param daysLow
     *            the daysLow to set
     */
    public void setDaysLow(Float daysLow) {
        DaysLow = daysLow;
    }

    /**
     * @return the daysHigh
     */
    public Float getDaysHigh() {
        return DaysHigh;
    }

    /**
     * @param daysHigh
     *            the daysHigh to set
     */
    public void setDaysHigh(Float daysHigh) {
        DaysHigh = daysHigh;
    }

    /**
     * @return the yearLow
     */
    public Float getYearLow() {
        return YearLow;
    }

    /**
     * @param yearLow
     *            the yearLow to set
     */
    public void setYearLow(Float yearLow) {
        YearLow = yearLow;
    }

    /**
     * @return the yearHigh
     */
    public Float getYearHigh() {
        return YearHigh;
    }

    /**
     * @param yearHigh
     *            the yearHigh to set
     */
    public void setYearHigh(Float yearHigh) {
        YearHigh = yearHigh;
    }

    /**
     * @return the holdingsGainPercent
     */
    public String getHoldingsGainPercent() {
        return HoldingsGainPercent;
    }

    /**
     * @param holdingsGainPercent
     *            the holdingsGainPercent to set
     */
    public void setHoldingsGainPercent(String holdingsGainPercent) {
        HoldingsGainPercent = holdingsGainPercent;
    }

    /**
     * @return the annualizedGain
     */
    public String getAnnualizedGain() {
        return AnnualizedGain;
    }

    /**
     * @param annualizedGain
     *            the annualizedGain to set
     */
    public void setAnnualizedGain(String annualizedGain) {
        AnnualizedGain = annualizedGain;
    }

    /**
     * @return the holdingsGain
     */
    public String getHoldingsGain() {
        return HoldingsGain;
    }

    /**
     * @param holdingsGain
     *            the holdingsGain to set
     */
    public void setHoldingsGain(String holdingsGain) {
        HoldingsGain = holdingsGain;
    }

    /**
     * @return the holdingsGainPercentRealtime
     */
    public String getHoldingsGainPercentRealtime() {
        return HoldingsGainPercentRealtime;
    }

    /**
     * @param holdingsGainPercentRealtime
     *            the holdingsGainPercentRealtime to set
     */
    public void setHoldingsGainPercentRealtime(
            String holdingsGainPercentRealtime) {
        HoldingsGainPercentRealtime = holdingsGainPercentRealtime;
    }

    /**
     * @return the holdingsGainRealtime
     */
    public String getHoldingsGainRealtime() {
        return HoldingsGainRealtime;
    }

    /**
     * @param holdingsGainRealtime
     *            the holdingsGainRealtime to set
     */
    public void setHoldingsGainRealtime(String holdingsGainRealtime) {
        HoldingsGainRealtime = holdingsGainRealtime;
    }

    /**
     * @return the moreInfo
     */
    public String getMoreInfo() {
        return MoreInfo;
    }

    /**
     * @param moreInfo
     *            the moreInfo to set
     */
    public void setMoreInfo(String moreInfo) {
        MoreInfo = moreInfo;
    }

    /**
     * @return the orderBookRealtime
     */
    public String getOrderBookRealtime() {
        return OrderBookRealtime;
    }

    /**
     * @param orderBookRealtime
     *            the orderBookRealtime to set
     */
    public void setOrderBookRealtime(String orderBookRealtime) {
        OrderBookRealtime = orderBookRealtime;
    }

    /**
     * @return the marketCapitalization
     */
    public String getMarketCapitalization() {
        return MarketCapitalization;
    }

    /**
     * @param marketCapitalization
     *            the marketCapitalization to set
     */
    public void setMarketCapitalization(String marketCapitalization) {
        MarketCapitalization = marketCapitalization;
    }

    /**
     * @return the marketCapRealtime
     */
    public String getMarketCapRealtime() {
        return MarketCapRealtime;
    }

    /**
     * @param marketCapRealtime
     *            the marketCapRealtime to set
     */
    public void setMarketCapRealtime(String marketCapRealtime) {
        MarketCapRealtime = marketCapRealtime;
    }

    /**
     * @return the eBITDA
     */
    public String getEBITDA() {
        return EBITDA;
    }

    /**
     * @param eBITDA
     *            the eBITDA to set
     */
    public void setEBITDA(String eBITDA) {
        EBITDA = eBITDA;
    }

    /**
     * @return the changeFromYearLow
     */
    public Float getChangeFromYearLow() {
        return ChangeFromYearLow;
    }

    /**
     * @param changeFromYearLow
     *            the changeFromYearLow to set
     */
    public void setChangeFromYearLow(Float changeFromYearLow) {
        ChangeFromYearLow = changeFromYearLow;
    }

    /**
     * @return the percentChangeFromYearLow
     */
    public Float getPercentChangeFromYearLow() {
        return PercentChangeFromYearLow;
    }

    /**
     * @param percentChangeFromYearLow
     *            the percentChangeFromYearLow to set
     */
    public void setPercentChangeFromYearLow(Float percentChangeFromYearLow) {
        PercentChangeFromYearLow = percentChangeFromYearLow;
    }

    /**
     * @return the lastTradeRealtimeWithTime
     */
    public String getLastTradeRealtimeWithTime() {
        return LastTradeRealtimeWithTime;
    }

    /**
     * @param lastTradeRealtimeWithTime
     *            the lastTradeRealtimeWithTime to set
     */
    public void setLastTradeRealtimeWithTime(String lastTradeRealtimeWithTime) {
        LastTradeRealtimeWithTime = lastTradeRealtimeWithTime;
    }

    /**
     * @return the changePercentRealtime
     */
    public String getChangePercentRealtime() {
        return ChangePercentRealtime;
    }

    /**
     * @param changePercentRealtime
     *            the changePercentRealtime to set
     */
    public void setChangePercentRealtime(String changePercentRealtime) {
        ChangePercentRealtime = changePercentRealtime;
    }

    /**
     * @return the changeFromYearHigh
     */
    public Float getChangeFromYearHigh() {
        return ChangeFromYearHigh;
    }

    /**
     * @param changeFromYearHigh
     *            the changeFromYearHigh to set
     */
    public void setChangeFromYearHigh(Float changeFromYearHigh) {
        ChangeFromYearHigh = changeFromYearHigh;
    }

    /**
     * @return the percebtChangeFromYearHigh
     */
    public Float getPercebtChangeFromYearHigh() {
        return PercebtChangeFromYearHigh;
    }

    /**
     * @param percebtChangeFromYearHigh
     *            the percebtChangeFromYearHigh to set
     */
    public void setPercebtChangeFromYearHigh(Float percebtChangeFromYearHigh) {
        PercebtChangeFromYearHigh = percebtChangeFromYearHigh;
    }

    /**
     * @return the lastTradeWithTime
     */
    public String getLastTradeWithTime() {
        return LastTradeWithTime;
    }

    /**
     * @param lastTradeWithTime
     *            the lastTradeWithTime to set
     */
    public void setLastTradeWithTime(String lastTradeWithTime) {
        LastTradeWithTime = lastTradeWithTime;
    }

    /**
     * @return the lastTradePriceOnly
     */
    public Float getLastTradePriceOnly() {
        return LastTradePriceOnly;
    }

    /**
     * @param lastTradePriceOnly
     *            the lastTradePriceOnly to set
     */
    public void setLastTradePriceOnly(Float lastTradePriceOnly) {
        LastTradePriceOnly = lastTradePriceOnly;
    }

    /**
     * @return the highLimit
     */
    public String getHighLimit() {
        return HighLimit;
    }

    /**
     * @param highLimit
     *            the highLimit to set
     */
    public void setHighLimit(String highLimit) {
        HighLimit = highLimit;
    }

    /**
     * @return the lowLimit
     */
    public String getLowLimit() {
        return LowLimit;
    }

    /**
     * @param lowLimit
     *            the lowLimit to set
     */
    public void setLowLimit(String lowLimit) {
        LowLimit = lowLimit;
    }

    /**
     * @return the daysRange
     */
    public String getDaysRange() {
        return DaysRange;
    }

    /**
     * @param daysRange
     *            the daysRange to set
     */
    public void setDaysRange(String daysRange) {
        DaysRange = daysRange;
    }

    /**
     * @return the daysRangeRealtime
     */
    public String getDaysRangeRealtime() {
        return DaysRangeRealtime;
    }

    /**
     * @param daysRangeRealtime
     *            the daysRangeRealtime to set
     */
    public void setDaysRangeRealtime(String daysRangeRealtime) {
        DaysRangeRealtime = daysRangeRealtime;
    }

    /**
     * @return the fiftydayMovingAverage
     */
    public Float getFiftydayMovingAverage() {
        return FiftydayMovingAverage;
    }

    /**
     * @param fiftydayMovingAverage
     *            the fiftydayMovingAverage to set
     */
    public void setFiftydayMovingAverage(Float fiftydayMovingAverage) {
        FiftydayMovingAverage = fiftydayMovingAverage;
    }

    /**
     * @return the twoHundreddayMovingAverage
     */
    public Float getTwoHundreddayMovingAverage() {
        return TwoHundreddayMovingAverage;
    }

    /**
     * @param twoHundreddayMovingAverage
     *            the twoHundreddayMovingAverage to set
     */
    public void setTwoHundreddayMovingAverage(Float twoHundreddayMovingAverage) {
        TwoHundreddayMovingAverage = twoHundreddayMovingAverage;
    }

    /**
     * @return the changeFromTwoHundreddayMovingAverage
     */
    public Float getChangeFromTwoHundreddayMovingAverage() {
        return ChangeFromTwoHundreddayMovingAverage;
    }

    /**
     * @param changeFromTwoHundreddayMovingAverage
     *            the changeFromTwoHundreddayMovingAverage to set
     */
    public void setChangeFromTwoHundreddayMovingAverage(
            Float changeFromTwoHundreddayMovingAverage) {
        ChangeFromTwoHundreddayMovingAverage = changeFromTwoHundreddayMovingAverage;
    }

    /**
     * @return the percentChangeFromTwoHundreddayMovingAverage
     */
    public Float getPercentChangeFromTwoHundreddayMovingAverage() {
        return PercentChangeFromTwoHundreddayMovingAverage;
    }

    /**
     * @param percentChangeFromTwoHundreddayMovingAverage
     *            the percentChangeFromTwoHundreddayMovingAverage to set
     */
    public void setPercentChangeFromTwoHundreddayMovingAverage(
            Float percentChangeFromTwoHundreddayMovingAverage) {
        PercentChangeFromTwoHundreddayMovingAverage = percentChangeFromTwoHundreddayMovingAverage;
    }

    /**
     * @return the changeFromFiftydayMovingAverage
     */
    public Float getChangeFromFiftydayMovingAverage() {
        return ChangeFromFiftydayMovingAverage;
    }

    /**
     * @param changeFromFiftydayMovingAverage
     *            the changeFromFiftydayMovingAverage to set
     */
    public void setChangeFromFiftydayMovingAverage(
            Float changeFromFiftydayMovingAverage) {
        ChangeFromFiftydayMovingAverage = changeFromFiftydayMovingAverage;
    }

    /**
     * @return the percentChangeFromFiftydayMovingAverage
     */
    public Float getPercentChangeFromFiftydayMovingAverage() {
        return PercentChangeFromFiftydayMovingAverage;
    }

    /**
     * @param percentChangeFromFiftydayMovingAverage
     *            the percentChangeFromFiftydayMovingAverage to set
     */
    public void setPercentChangeFromFiftydayMovingAverage(
            Float percentChangeFromFiftydayMovingAverage) {
        PercentChangeFromFiftydayMovingAverage = percentChangeFromFiftydayMovingAverage;
    }

    /**
     * @return the name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return Notes;
    }

    /**
     * @param notes
     *            the notes to set
     */
    public void setNotes(String notes) {
        Notes = notes;
    }

    /**
     * @return the open
     */
    public Float getOpen() {
        return Open;
    }

    /**
     * @param open
     *            the open to set
     */
    public void setOpen(Float open) {
        Open = open;
    }

    /**
     * @return the previousClose
     */
    public Float getPreviousClose() {
        return PreviousClose;
    }

    /**
     * @param previousClose
     *            the previousClose to set
     */
    public void setPreviousClose(Float previousClose) {
        PreviousClose = previousClose;
    }

    /**
     * @return the pricePaid
     */
    public String getPricePaid() {
        return PricePaid;
    }

    /**
     * @param pricePaid
     *            the pricePaid to set
     */
    public void setPricePaid(String pricePaid) {
        PricePaid = pricePaid;
    }

    /**
     * @return the changeinPercent
     */
    public Float getChangeinPercent() {
        return ChangeinPercent;
    }

    /**
     * @param changeinPercent
     *            the changeinPercent to set
     */
    public void setChangeinPercent(Float changeinPercent) {
        ChangeinPercent = changeinPercent;
    }

    /**
     * @return the priceSales
     */
    public Float getPriceSales() {
        return PriceSales;
    }

    /**
     * @param priceSales
     *            the priceSales to set
     */
    public void setPriceSales(Float priceSales) {
        PriceSales = priceSales;
    }

    /**
     * @return the priceBook
     */
    public Float getPriceBook() {
        return PriceBook;
    }

    /**
     * @param priceBook
     *            the priceBook to set
     */
    public void setPriceBook(Float priceBook) {
        PriceBook = priceBook;
    }

    /**
     * @return the exDividendDate
     */
    public String getExDividendDate() {
        return ExDividendDate;
    }

    /**
     * @param exDividendDate
     *            the exDividendDate to set
     */
    public void setExDividendDate(String exDividendDate) {
        ExDividendDate = exDividendDate;
    }

    /**
     * @return the pERatio
     */
    public String getPERatio() {
        return PERatio;
    }

    /**
     * @param pERatio
     *            the pERatio to set
     */
    public void setPERatio(String pERatio) {
        PERatio = pERatio;
    }

    /**
     * @return the dividendPayDate
     */
    public String getDividendPayDate() {
        return DividendPayDate;
    }

    /**
     * @param dividendPayDate
     *            the dividendPayDate to set
     */
    public void setDividendPayDate(String dividendPayDate) {
        DividendPayDate = dividendPayDate;
    }

    /**
     * @return the pERatioRealtime
     */
    public String getPERatioRealtime() {
        return PERatioRealtime;
    }

    /**
     * @param pERatioRealtime
     *            the pERatioRealtime to set
     */
    public void setPERatioRealtime(String pERatioRealtime) {
        PERatioRealtime = pERatioRealtime;
    }

    /**
     * @return the pEGRatio
     */
    public String getPEGRatio() {
        return PEGRatio;
    }

    /**
     * @param pEGRatio
     *            the pEGRatio to set
     */
    public void setPEGRatio(String pEGRatio) {
        PEGRatio = pEGRatio;
    }

    /**
     * @return the priceEPSEstimateCurrentYear
     */
    public String getPriceEPSEstimateCurrentYear() {
        return PriceEPSEstimateCurrentYear;
    }

    /**
     * @param priceEPSEstimateCurrentYear
     *            the priceEPSEstimateCurrentYear to set
     */
    public void setPriceEPSEstimateCurrentYear(
            String priceEPSEstimateCurrentYear) {
        PriceEPSEstimateCurrentYear = priceEPSEstimateCurrentYear;
    }

    /**
     * @return the priceEPSEstimateNextYear
     */
    public String getPriceEPSEstimateNextYear() {
        return PriceEPSEstimateNextYear;
    }

    /**
     * @param priceEPSEstimateNextYear
     *            the priceEPSEstimateNextYear to set
     */
    public void setPriceEPSEstimateNextYear(String priceEPSEstimateNextYear) {
        PriceEPSEstimateNextYear = priceEPSEstimateNextYear;
    }

    /**
     * @return the sharesOwned
     */
    public String getSharesOwned() {
        return SharesOwned;
    }

    /**
     * @param sharesOwned
     *            the sharesOwned to set
     */
    public void setSharesOwned(String sharesOwned) {
        SharesOwned = sharesOwned;
    }

    /**
     * @return the shortRatio
     */
    public String getShortRatio() {
        return ShortRatio;
    }

    /**
     * @param shortRatio
     *            the shortRatio to set
     */
    public void setShortRatio(String shortRatio) {
        ShortRatio = shortRatio;
    }

    /**
     * @return the lastTradeTime
     */
    public String getLastTradeTime() {
        return LastTradeTime;
    }

    /**
     * @param lastTradeTime
     *            the lastTradeTime to set
     */
    public void setLastTradeTime(String lastTradeTime) {
        LastTradeTime = lastTradeTime;
    }

    /**
     * @return the tickerTrend
     */
    public String getTickerTrend() {
        return TickerTrend;
    }

    /**
     * @param tickerTrend
     *            the tickerTrend to set
     */
    public void setTickerTrend(String tickerTrend) {
        TickerTrend = tickerTrend;
    }

    /**
     * @return the oneyrTargetPrice
     */
    public String getOneyrTargetPrice() {
        return OneyrTargetPrice;
    }

    /**
     * @param oneyrTargetPrice
     *            the oneyrTargetPrice to set
     */
    public void setOneyrTargetPrice(String oneyrTargetPrice) {
        OneyrTargetPrice = oneyrTargetPrice;
    }

    /**
     * @return the volume
     */
    public Long getVolume() {
        return Volume;
    }

    /**
     * @param volume
     *            the volume to set
     */
    public void setVolume(Long volume) {
        Volume = volume;
    }

    /**
     * @return the holdingsValue
     */
    public String getHoldingsValue() {
        return HoldingsValue;
    }

    /**
     * @param holdingsValue
     *            the holdingsValue to set
     */
    public void setHoldingsValue(String holdingsValue) {
        HoldingsValue = holdingsValue;
    }

    /**
     * @return the holdingsValueRealtime
     */
    public String getHoldingsValueRealtime() {
        return HoldingsValueRealtime;
    }

    /**
     * @param holdingsValueRealtime
     *            the holdingsValueRealtime to set
     */
    public void setHoldingsValueRealtime(String holdingsValueRealtime) {
        HoldingsValueRealtime = holdingsValueRealtime;
    }

    /**
     * @return the yearRange
     */
    public String getYearRange() {
        return YearRange;
    }

    /**
     * @param yearRange
     *            the yearRange to set
     */
    public void setYearRange(String yearRange) {
        YearRange = yearRange;
    }

    /**
     * @return the daysValueChange
     */
    public String getDaysValueChange() {
        return DaysValueChange;
    }

    /**
     * @param daysValueChange
     *            the daysValueChange to set
     */
    public void setDaysValueChange(String daysValueChange) {
        DaysValueChange = daysValueChange;
    }

    /**
     * @return the daysValueChangeRealtime
     */
    public String getDaysValueChangeRealtime() {
        return DaysValueChangeRealtime;
    }

    /**
     * @param daysValueChangeRealtime
     *            the daysValueChangeRealtime to set
     */
    public void setDaysValueChangeRealtime(String daysValueChangeRealtime) {
        DaysValueChangeRealtime = daysValueChangeRealtime;
    }

    /**
     * @return the stockExchange
     */
    public String getStockExchange() {
        return StockExchange;
    }

    /**
     * @param stockExchange
     *            the stockExchange to set
     */
    public void setStockExchange(String stockExchange) {
        StockExchange = stockExchange;
    }

    /**
     * @return the dividendYield
     */
    public String getDividendYield() {
        return DividendYield;
    }

    /**
     * @param dividendYield
     *            the dividendYield to set
     */
    public void setDividendYield(String dividendYield) {
        DividendYield = dividendYield;
    }

    /**
     * @return the percentChange
     */
    public Float getPercentChange() {
        return PercentChange;
    }

    /**
     * @param percentChange
     *            the percentChange to set
     */
    public void setPercentChange(Float percentChange) {
        PercentChange = percentChange;
    }
}