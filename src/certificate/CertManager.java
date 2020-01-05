/**
 * 
 */
package certificate;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ezhang
 *
 */
public class CertManager {
	public static void main(String[] args) throws Exception {
		try {
			File file = new File("src/bing.cer");
			InputStream inStream = new FileInputStream(file);
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate oCert = (X509Certificate) cf.generateCertificate(inStream);
			inStream.close();
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
			String info = null;

			info = String.valueOf(oCert.getVersion());
			System.out.println("证书版本:" + info);
			info = oCert.getSerialNumber().toString(16);
			System.out.println("证书序列号:" + info);
			Date beforedate = oCert.getNotBefore();
			info = dateformat.format(beforedate);
			System.out.println("证书生效日期:" + info);
			Date afterdate = oCert.getNotAfter();
			info = dateformat.format(afterdate);
			System.out.println("证书失效日期:" + info);
			info = oCert.getSubjectDN().getName();
			System.out.println("证书拥有者:" + info);
			info = oCert.getIssuerDN().getName();
			System.out.println("证书颁发者:" + info);
			info = oCert.getSigAlgName();
			System.out.println("证书签名算法:" + info);
			PublicKey publicKey = oCert.getPublicKey();
			System.out.println("Public key:" + publicKey.getFormat());
			byte[] signature = oCert.getSignature();
			System.out.println("Signature:" + new String(signature));
			
		} catch (Exception e) {
			System.out.println("解析证书出错！");
			e.printStackTrace();
		}
	}
}
