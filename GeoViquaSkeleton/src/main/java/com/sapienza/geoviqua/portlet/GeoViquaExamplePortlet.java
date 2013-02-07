package com.sapienza.geoviqua.portlet;


import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.io.DocumentSource;

import com.sapienza.geoviqua.service.GeoViquaService;


/**
 * @author kmadonna
 * 
 */
public class GeoViquaExamplePortlet extends BasePortlet {
	
	private static final String XSL_FILE= "/xsl/documentRecordById.xsl";
	private static final String param_uuid = "uuid";
		
	@Override
    public void doView(RenderRequest req, RenderResponse res)
		throws IOException, PortletException {
			res.setContentType("text/html");
	        String[] uuid = req.getParameterValues(param_uuid);
	        // Correct check here would be to verify whether uuid is not empty or null value.
	        // When null it should raise an error or redirect to a portlet error view
	        // or fill in the view with an error message
	        String path = getFullXslPath(req, XSL_FILE);
	        _log.info("doView(): path " + path);
	        if(uuid != null){
	        	_log.info("doView(): uuid " + uuid.length);	        	
	        }
	        else // when null a message is sent to logger  
	        	_log.info("doView(): uuid is null");

	        GeoViquaService service = new GeoViquaService();
	        Document xmlDocument = service.retrieveDocument(uuid);
	        TransformerFactory tFactory = TransformerFactory.newInstance();
            DocumentSource docSource = new DocumentSource(xmlDocument);
            Transformer transformer;
			try {
				transformer = tFactory.newTransformer(new StreamSource(path));
				 try {
					   transformer.transform(docSource, new StreamResult(res.getPortletOutputStream()));
				} catch (TransformerException e) {
					_log.error("TransformerException e "+e.toString());
				}
			} catch(TransformerConfigurationException e){
				_log.error("TransformerConfigurationException e "+e.toString());
			}
            /** 
			 *  Not forwarded in the usual way. Results are streamed out from previous
			 *  call. 
			 **/
			
		    // include(viewJSP, req, res);
	}
	
	private static Log _log = LogFactory.getLog(GeoViquaExamplePortlet.class);

}