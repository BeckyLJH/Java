package com.leec.lmodules_article.util.Configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
/*如果要对一个属性设置文件.properties文件进行操作,我们当然想到用java.util.Properties类.
 *对于只进行属性读取,这个类已经足够好了,但需要在Properties实例改变之后调用.store()或者
 *.save()需要重新输出的时候,这个类就只会简单的把所有的属性简单的输出,整个文件格式和属
 *性的顺序都改变了,原来的注释也消失了.
 *附件中是一个改进过的properites文件读写类,在java.util.Properties的基础上进行改进,在读
 *取properties文件的时候把注释和顺序格式都记录下来,操作时候也把添加顺序记录了,所以很好
 *的解决了java.util.Properties类在输出的不足,同时它提供addComment()方法,可以添加注释,
 *这样,经过处理的properties的可读性就能继续保持下来了.
 *看下面的示范代码:
       FileInputStream input = new FileInputStream("e:/input.properties");
       SafeProperties safeProp = new SafeProperties();
       safeProp.load(input);
       input.close();
       safeProp.addComment("New Comment");
       safeProp.put("New-Key", "New====Value");
       FileOutputStream output = new FileOutputStream("e:/output.properties");
       safeProp.store(output, null);
       output.close();
 */
public class SafeProperties extends Properties {
	private static final long serialVersionUID = 5011694856722313621L;

	private static final String keyValueSeparators = "=: \t\r\n\f";

	private static final String strictKeyValueSeparators = "=:";

	private static final String specialSaveChars = "=: \t\r\n\f#!";

	private static final String whiteSpaceChars = " \t\r\n\f";

	private PropertiesContext context = new PropertiesContext();

	public PropertiesContext getContext() {
		return context;
	}

	public synchronized void load(InputStream inStream) throws IOException {

		BufferedReader in;

		in = new BufferedReader(new InputStreamReader(inStream, "8859_1"));
		while (true) {
			// Get next line
			String line = in.readLine();
			// intract property/comment string
			String intactLine = line;
			if (line == null)
				return;

			if (line.length() > 0) {

				// Find start of key
				int len = line.length();
				int keyStart;
				for (keyStart = 0; keyStart < len; keyStart++)
					if (whiteSpaceChars.indexOf(line.charAt(keyStart)) == -1)
						break;

				// Blank lines are ignored
				if (keyStart == len)
					continue;

				// Continue lines that end in slashes if they are not comments
				char firstChar = line.charAt(keyStart);

				if ((firstChar != '#') && (firstChar != '!')) {
					while (continueLine(line)) {
						String nextLine = in.readLine();
						intactLine = intactLine + "\n" + nextLine;
						if (nextLine == null)
							nextLine = "";
						String loppedLine = line.substring(0, len - 1);
						// Advance beyond whitespace on new line
						int startIndex;
						for (startIndex = 0; startIndex < nextLine.length(); startIndex++)
							if (whiteSpaceChars.indexOf(nextLine.charAt(startIndex)) == -1)
								break;
						nextLine = nextLine.substring(startIndex, nextLine.length());
						line = new String(loppedLine + nextLine);
						len = line.length();
					}

					// Find separation between key and value
					int separatorIndex;
					for (separatorIndex = keyStart; separatorIndex < len; separatorIndex++) {
						char currentChar = line.charAt(separatorIndex);
						if (currentChar == '\\')
							separatorIndex++;
						else if (keyValueSeparators.indexOf(currentChar) != -1)
							break;
					}

					// Skip over whitespace after key if any
					int valueIndex;
					for (valueIndex = separatorIndex; valueIndex < len; valueIndex++)
						if (whiteSpaceChars.indexOf(line.charAt(valueIndex)) == -1)
							break;

					// Skip over one non whitespace key value separators if any
					if (valueIndex < len)
						if (strictKeyValueSeparators.indexOf(line.charAt(valueIndex)) != -1)
							valueIndex++;

					// Skip over white space after other separators if any
					while (valueIndex < len) {
						if (whiteSpaceChars.indexOf(line.charAt(valueIndex)) == -1)
							break;
						valueIndex++;
					}
					String key = line.substring(keyStart, separatorIndex);
					String value = (separatorIndex < len) ? line.substring(valueIndex, len) : "";

					// Convert then store key and value
					key = loadConvert(key);
					value = loadConvert(value);
					//memorize the property also with the whold string
					put(key, value, intactLine);
				} else {
					//memorize the comment string
					context.addCommentLine(intactLine);
				}
			} else {
				//memorize the string even the string is empty
				context.addCommentLine(intactLine);
			}
		}
	}
	
	/*
	 * Converts encoded &#92;uxxxx to unicode chars and changes special saved
	 * chars to their original forms
	 */
	private String loadConvert(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						outBuffer.append('\t'); /* ibm@7211 */

					else if (aChar == 'r')
						outBuffer.append('\r'); /* ibm@7211 */
					else if (aChar == 'n') {
						/*
						 * ibm@8897 do not convert a \n to a line.separator
						 * because on some platforms line.separator is a String
						 * of "\r\n". When a Properties class is saved as a file
						 * (store()) and then restored (load()) the restored
						 * input MUST be the same as the output (so that
						 * Properties.equals() works).
						 * 
						 */
						outBuffer.append('\n'); /* ibm@8897 ibm@7211 */
					} else if (aChar == 'f')
						outBuffer.append('\f'); /* ibm@7211 */
					else
						/* ibm@7211 */
						outBuffer.append(aChar); /* ibm@7211 */
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	public synchronized void store(OutputStream out, String header) throws IOException {
		BufferedWriter awriter;
		awriter = new BufferedWriter(new OutputStreamWriter(out, "8859_1"));
		if (header != null)
			writeln(awriter, "#" + header);
		List entrys = context.getCommentOrEntrys();
		for (Iterator iter = entrys.iterator(); iter.hasNext();) {
			Object obj = iter.next();
			if (obj.toString() != null) {
				writeln(awriter, obj.toString());
			}
		}
		awriter.flush();
	}

	private static void writeln(BufferedWriter bw, String s) throws IOException {
		bw.write(s);
		bw.newLine();
	}

	private boolean continueLine(String line) {
		int slashCount = 0;
		int index = line.length() - 1;
		while ((index >= 0) && (line.charAt(index--) == '\\'))
			slashCount++;
		return (slashCount % 2 == 1);
	}

	/*
	 * Converts unicodes to encoded &#92;uxxxx and writes out any of the
	 * characters in specialSaveChars with a preceding slash
	 */
	private String saveConvert(String theString, boolean escapeSpace) {
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len * 2);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			switch (aChar) {
			case ' ':
				if (x == 0 || escapeSpace)
					outBuffer.append('\\');

				outBuffer.append(' ');
				break;
			case '\\':
				outBuffer.append('\\');
				outBuffer.append('\\');
				break;
			case '\t':
				outBuffer.append('\\');
				outBuffer.append('t');
				break;
			case '\n':
				outBuffer.append('\\');
				outBuffer.append('n');
				break;
			case '\r':
				outBuffer.append('\\');
				outBuffer.append('r');
				break;
			case '\f':
				outBuffer.append('\\');
				outBuffer.append('f');
				break;
			default:
				if ((aChar < 0x0020) || (aChar > 0x007e)) {
					outBuffer.append('\\');
					outBuffer.append('u');
					outBuffer.append(toHex((aChar >> 12) & 0xF));
					outBuffer.append(toHex((aChar >> 8) & 0xF));
					outBuffer.append(toHex((aChar >> 4) & 0xF));
					outBuffer.append(toHex(aChar & 0xF));
				} else {
					if (specialSaveChars.indexOf(aChar) != -1)
						outBuffer.append('\\');
					outBuffer.append(aChar);
				}
			}
		}
		return outBuffer.toString();
	}

	/**
	 * Convert a nibble to a hex character
	 * 
	 * @param nibble
	 *            the nibble to convert.
	 */
	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}

	/** A table of hex digits */
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	public synchronized Object put(Object key, Object value) {
		context.putOrUpdate(key.toString(), value.toString());
		return super.put(key, value);
	}

	public synchronized Object put(Object key, Object value, String line) {
		context.putOrUpdate(key.toString(), value.toString(), line);
		return super.put(key, value);
	}


	public synchronized Object remove(Object key) {
		context.remove(key.toString());
		return super.remove(key);
	}

	class PropertiesContext {
		private List commentOrEntrys = new ArrayList();

		public List getCommentOrEntrys() {
			return commentOrEntrys;
		}

		public void addCommentLine(String line) {
			commentOrEntrys.add(line);
		}

		public void putOrUpdate(PropertyEntry pe) {
			remove(pe.getKey());
			commentOrEntrys.add(pe);
		}

		public void putOrUpdate(String key, String value, String line) {
			PropertyEntry pe = new PropertyEntry(key, value, line);
			remove(key);
			commentOrEntrys.add(pe);
		}

		/*public void putOrUpdate(String key, String value) {
			PropertyEntry pe = new PropertyEntry(key, value);
			remove(key);
			commentOrEntrys.add(pe);
		}

		public void remove(String key) {
			for (int i = 0; i < commentOrEntrys.size(); i++) {
				Object obj = commentOrEntrys.get(i);
				if (obj instanceof PropertyEntry) {
					if (obj != null) {
						if (key.equals(((PropertyEntry) obj).getKey())) {
							commentOrEntrys.remove(obj);
						}
					}
				}
			}
		}*/
		/*上述注释中的putOrUpdate与remove方法重新设置原有属性的值时后，程序不是在
		原来的位置修改属性，而是将原有属性删除，再作为新属性添加，这样属性的位置
		就发生了变动。
		下面的putOrUpdate与remove方法实现了在原来位置修改属性
		*/
		public void putOrUpdate(String key, String value) {
			PropertyEntry pe = new PropertyEntry(key, value);
			int index = remove(key);
			commentOrEntrys.add(index,pe); 
		}
		public int remove(String key) {
		    for (int index = 0; index < commentOrEntrys.size(); index++) {
			Object obj = commentOrEntrys.get(index);
			if (obj instanceof PropertyEntry) {
				if (obj != null) {
				if (key.equals(((PropertyEntry) obj).getKey())) {
									commentOrEntrys.remove(obj);
									return index;
								}
							}
						}
			  }
			  return commentOrEntrys.size();
		}

		class PropertyEntry {
			private String key;

			private String value;

			private String line;

			public String getLine() {
				return line;
			}

			public void setLine(String line) {
				this.line = line;
			}

			public PropertyEntry(String key, String value) {
				this.key = key;
				this.value = value;
			}

			/**
			 * @param key
			 * @param value
			 * @param line
			 */
			public PropertyEntry(String key, String value, String line) {
				this(key, value);
				this.line = line;
			}

			public String getKey() {
				return key;
			}

			public void setKey(String key) {
				this.key = key;
			}

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}

			public String toString() {
				if (line != null) {
					return line;
				}
				if (key != null && value != null) {
					String k = saveConvert(key, true);
					String v = saveConvert(value, false);
					return k + "=" + v;
				}
				return null;
			}
		}
	}

	/**
	 * @param string
	 */
	public void addComment(String comment) {
		if (comment != null) {
			context.addCommentLine("#" + comment);			
		}
	}

}
