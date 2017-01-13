
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.clickfactura.util;

import mx.clickfactura.exception.CustomBadRequestException;
import mx.clickfactura.exception.CustomNotFoundException;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SISTEMAS03
 */
@Component
public class TipoCambioUtil {

    /*public static void main(String[] args) throws IOException {

        TipoCambioUtil m = new TipoCambioUtil();
        try {
            m.getTipoCambio("2016-03-16");
        } catch (ParseException ex) {
            Logger.getLogger(TipoCambioUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/


    public String getTipoCambio(String fecha) throws CustomBadRequestException, CustomNotFoundException, Exception {

        Pattern pattern = Pattern.compile("^\\d{4}\\-\\d{2}\\-\\d{2}$");
        Matcher matcher = null;

        matcher = pattern.matcher(fecha.trim());

        if (!matcher.matches()) {
            throw new CustomBadRequestException("Fecha invalida, el formato debe ser: yyyy-MM-dd");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = new GregorianCalendar();

        cal.setTime(sdf.parse(fecha));

        String dia = (cal.get(Calendar.DATE) < 10) ? "0" + cal.get(Calendar.DATE) : cal.get(Calendar.DATE) + "";
        String mes = ((cal.get(Calendar.MONTH) + 1) < 10) ? "0" + (cal.get(Calendar.MONTH) + 1) : (cal.get(Calendar.MONTH) + 1) + "";
        String anio = cal.get(Calendar.YEAR) + "";

        String fechaInicial = dia + "%2F" + mes + "%2F" + anio;

        CloseableHttpClient client = HttpClients.createDefault();
        CookieStore cookies = new BasicCookieStore();
        String[] fechaSeparada = fecha.split("-");
        HttpGet get = new HttpGet("http://www.dof.gob.mx/indicadores_detalle.php?cod_tipo_indicador=158&dfecha=" + fechaInicial + "&hfecha=" + fechaInicial);

        HttpContext httpContext = new BasicHttpContext();
        httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookies);
        CloseableHttpResponse response = client.execute(get, httpContext);

        //System.out.println(response.toString());      
        //System.out.println(response.getStatusLine());
        //System.out.println(response.getEntity().getContentLength());
        InputStream in = response.getEntity().getContent();
        Header encoding = response.getEntity().getContentEncoding();

        String body = IOUtils.toString(in, "UTF-8");
        //System.out.println(body);

        Document doc = Jsoup.parse(body, "UTF-8");

        doc = doc.normalise();

        //System.out.println(doc.toString());
        Elements e = doc.select("table");

        Iterator iterator = e.iterator();

        pattern = Pattern.compile("^\\d{2}\\.\\d{6}$");
        matcher = null;

        String tipoCambio = null;

        while (iterator.hasNext()) {
            Element xd = (Element) iterator.next();
            if (xd.getElementsByClass("txt").hasAttr("height")) {
                if (xd.getElementsByClass("txt").text().split(" ").length == 6) {

                    String cambio = xd.getElementsByClass("txt").text().split(" ")[5];
                    matcher = pattern.matcher(cambio.trim());

                    if (matcher.matches()) {
                        tipoCambio = cambio;
                        //System.out.println(tipoCambio);
                        break;
                    }

                }

            }

        }

        client.close();
        response.close();

        if (tipoCambio == null || tipoCambio.isEmpty()) {
            throw new CustomNotFoundException("No hay un tipo de cambio para el día: " + fecha);

        }

        return tipoCambio;

    }

}
