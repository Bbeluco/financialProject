package com.study.financial.sevices;

import com.study.financial.constants.HttpRequestAuthTransferBalanceConstants;
import com.study.financial.exceptions.InstableSystemRunning;
import com.study.financial.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.study.financial.constants.HttpRequestAuthTransferBalanceConstants.AUTHORIZATION_APPROVED_STRING;

@Service
public class HttpRequestAuthTransferBalanceService {
    
    private HttpURLConnection conn;

    public boolean isTransferAuthorizedByThirdPartApp() throws InstableSystemRunning {
        try {
            configureConnection();
            return StringUtils.convertInputStreamIntoString(this.conn.getInputStream()).contains(AUTHORIZATION_APPROVED_STRING);
        } catch (IOException e) {
            throw new InstableSystemRunning("Sorry, our system is unstable by now. Try again later");
        }
    }

    private void configureConnection() throws IOException {
        URL url = new URL(HttpRequestAuthTransferBalanceConstants.URL);
        openConnectionTo(url);
        conn.setRequestMethod(HttpRequestAuthTransferBalanceConstants.REQUEST_METHOD);
        setTimeoutsTimers();
    }

    private void openConnectionTo(URL url) throws IOException {
        this.conn = (HttpURLConnection) url.openConnection();
    }

    private void setTimeoutsTimers() {
        conn.setConnectTimeout(HttpRequestAuthTransferBalanceConstants.CONNECTION_TIMEOUT);
        conn.setReadTimeout(HttpRequestAuthTransferBalanceConstants.READ_TIMEOUT);
    }
}
