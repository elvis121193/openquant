package org.openquant.backtest;

/*
Copyright (c) 2010, Jay Logelin
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following 
conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer 
in the documentation and/or other materials provided with the distribution.  Neither the name of the JQuant nor the names of its 
contributors may be used to endorse or promote products derived from this software without specific prior written permission.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, 
BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT 
SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class CandleSeriesTestContext {

	protected Log log = LogFactory.getLog(CandleSeriesTestContext.class);

	private CandleSeries series;

	private OrderManager orderManager = new OrderManager();

	public CandleSeriesTestContext() {
	}

	public void reset() {
		orderManager.getClosedPositions().clear();
		orderManager.getOpenPositions().clear();
		orderManager.setSymbol(null);
	}

	public OrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public CandleSeries getSeries() {
		return series;
	}

	public void setSeries(CandleSeries series) {
		this.series = series;
		orderManager.setSymbol(series.getSymbol());
	}

	public int barsCount() {
		return series.size();
	}

	public Series closeSeries() {
		return series.getCloseSeries();
	}

	public Series openSeries() {
		return series.getOpenSeries();
	}

	public Series highSeries() {
		return series.getHighSeries();
	}

	public Series lowSeries() {
		return series.getLowSeries();
	}

	public abstract void run();

	public double close(int barIndex) {
		return series.get(barIndex).getClosePrice();
	}

	public double open(int barIndex) {
		return series.get(barIndex).getClosePrice();
	}

	public double high(int barIndex) {
		return series.get(barIndex).getClosePrice();
	}

	public double low(int barIndex) {
		return series.get(barIndex).getClosePrice();
	}

	public Date date(int barIndex) {
		return series.get(barIndex).getDate();
	}

	public void buyAtMarket(int barIndex, double marketPrice, int quantity, String label) {
		orderManager.buyAtMarket(barIndex, marketPrice, quantity, series);
	}
	
	public void buyAtLimit(int barIndex, double limitPrice, int quantity, String label) {
		orderManager.buyAtLimit(barIndex, limitPrice, quantity, series);
	}

	public void sellAtLimit(int barIndex, Position position, double limitPrice, String label) {
		orderManager.sellAtLimit(barIndex, position, limitPrice, series);
	}

	public void sellAtStop(int barIndex, Position position, double stopPrice, String label) {
		orderManager.sellAtStop(barIndex, position, stopPrice, series);
	}

	public Position getLastOpenPosition() {
		return orderManager.getLastOpenPosition();
	}

	public boolean hasOpenPositions() {
		return orderManager.hasOpenPositions();
	}

	public void PrintLine(final String string) {
		System.out.println(string);
	}

}