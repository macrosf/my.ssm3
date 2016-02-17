package cn.oss.ssm3.spitter.mvc;

public class ImageUploadException extends RuntimeException {

	private static final long serialVersionUID = 2490652331313427949L;

	public ImageUploadException(String message) {
		super(message);
	}
  
	public ImageUploadException(String message, Throwable cause) {
	    super(message, cause);
	}
}
