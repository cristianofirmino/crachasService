package com.pdfservices.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.pdfservices.exception.BusinessException;
import com.pdfservices.model.EmployeeCard;
import com.pdfservices.model.Pictogram;

public class EmployeeCardsService {

	public byte[] createEmployeeCard(EmployeeCard card) throws BusinessException {

		Image employeeImage;
		PdfReader pdfreader;
		PdfStamper pdfStamper;
		Image pictogramImage;

		try {
			employeeImage = new Jpeg(Base64.decodeBase64(card.getFoto().split(",")[1]));
			pdfreader = new PdfReader(new ClassPathResource(card.getTemplate()).getInputStream());
		} catch (BadElementException | IOException e) {
			throw new BusinessException(e.getLocalizedMessage());
		}

		float x, y, z, w;
		int pages = pdfreader.getNumberOfPages();
		Rectangle pagesize;
		ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();

		try {
			pdfStamper = new PdfStamper(pdfreader, fileOutputStream);
		} catch (DocumentException | IOException e) {
			throw new BusinessException(e.getLocalizedMessage());
		}

		for (int i = 1; i <= pages; i++) {

			if (i == 1) {

				int scaleX = 73;
				int scaleY = 90;

				pagesize = pdfreader.getPageSize(i);
				x = (pagesize.getLeft() + pagesize.getRight()) / 2;
				y = ((pagesize.getBottom() + pagesize.getTop()) / 2) - 65;
				z = y - 18;
				w = z - 10;

				addEmployeeData(card.getMatricula(), card.getIdPeople(), card.getNome(), card.getCargo(),
						pdfStamper.getOverContent(i), x, y, z, w);
				addImage(employeeImage, scaleX, scaleY, x / 2, y + 18, i, pdfreader, pdfStamper);
			}

			if (i == 2) {
				pagesize = pdfreader.getPageSize(i);
				x = ((pagesize.getLeft() + pagesize.getRight()) / 2);
				y = ((pagesize.getBottom() + pagesize.getTop()) / 2);
				int scale = 41;
				List<Pictogram> pictograms = card.getPictogramas();

				for (int j = 0; j < pictograms.size(); j++) {

					if (j == 0) {
						try {
							Pictogram pictogram = pictograms.get(j);
							pictogramImage = new Jpeg(Base64.decodeBase64(pictogram.getImagem().split(",")[1]));
							addImage(pictogramImage, scale, scale, x - 72, y + 40, i, pdfreader, pdfStamper);
							addLabel(pictogram.getLabel(), pdfStamper.getOverContent(i), x - 52, y + 30);
						} catch (BadElementException | IOException e) {
							throw new BusinessException(e.getLocalizedMessage());
						}
					}

					if (j == 1) {
						try {
							Pictogram pictogram = pictograms.get(j);
							pictogramImage = new Jpeg(Base64.decodeBase64(pictogram.getImagem().split(",")[1]));
							addImage(pictogramImage, scale, scale, x - (float) 20.1, y + 40, i, pdfreader, pdfStamper);
							addLabel(pictogram.getLabel(), pdfStamper.getOverContent(i), x, y + 30);
						} catch (BadElementException | IOException e) {
							throw new BusinessException(e.getLocalizedMessage());
						}
					}

					if (j == 2) {
						try {
							Pictogram pictogram = pictograms.get(j);
							pictogramImage = new Jpeg(Base64.decodeBase64(pictogram.getImagem().split(",")[1]));
							addImage(pictogramImage, scale, scale, x + 32, y + 40, i, pdfreader, pdfStamper);
							addLabel(pictogram.getLabel(), pdfStamper.getOverContent(i), x + 52, y + 30);
						} catch (BadElementException | IOException e) {
							throw new BusinessException(e.getLocalizedMessage());
						}
					}

					if (j == 3) {
						try {
							Pictogram pictogram = pictograms.get(j);
							pictogramImage = new Jpeg(Base64.decodeBase64(pictogram.getImagem().split(",")[1]));
							addImage(pictogramImage, scale, scale, x - 72, y - 20, i, pdfreader, pdfStamper);
							addLabel(pictogram.getLabel(), pdfStamper.getOverContent(i), x - 52, y - 30);
						} catch (BadElementException | IOException e) {
							throw new BusinessException(e.getLocalizedMessage());
						}
					}

					if (j == 4) {
						try {
							Pictogram pictogram = pictograms.get(j);
							pictogramImage = new Jpeg(Base64.decodeBase64(pictogram.getImagem().split(",")[1]));
							addImage(pictogramImage, scale, scale, x - (float) 20.1, y - 20, i, pdfreader, pdfStamper);
							addLabel(pictogram.getLabel(), pdfStamper.getOverContent(i), x, y - 30);
						} catch (BadElementException | IOException e) {
							throw new BusinessException(e.getLocalizedMessage());
						}
					}

					if (j == 5) {
						try {
							Pictogram pictogram = pictograms.get(j);
							pictogramImage = new Jpeg(Base64.decodeBase64(pictogram.getImagem().split(",")[1]));
							addImage(pictogramImage, scale, scale, x + 32, y - 20, i, pdfreader, pdfStamper);
							addLabel(pictogram.getLabel(), pdfStamper.getOverContent(i), x + 52, y - 30);
						} catch (BadElementException | IOException e) {
							throw new BusinessException(e.getLocalizedMessage());
						}
					}

					if (j == 6) {
						try {
							Pictogram pictogram = pictograms.get(j);
							pictogramImage = new Jpeg(Base64.decodeBase64(pictogram.getImagem().split(",")[1]));
							addImage(pictogramImage, scale, scale, x - 72, y - 80, i, pdfreader, pdfStamper);
							addLabel(pictogram.getLabel(), pdfStamper.getOverContent(i), x - 52, y - 90);
						} catch (BadElementException | IOException e) {
							throw new BusinessException(e.getLocalizedMessage());
						}
					}

					if (j == 7) {
						try {
							Pictogram pictogram = pictograms.get(j);
							pictogramImage = new Jpeg(Base64.decodeBase64(pictogram.getImagem().split(",")[1]));
							addImage(pictogramImage, scale, scale, x - (float) 20.1, y - 80, i, pdfreader, pdfStamper);
							addLabel(pictogram.getLabel(), pdfStamper.getOverContent(i), x, y - 90);
						} catch (BadElementException | IOException e) {
							throw new BusinessException(e.getLocalizedMessage());
						}
					}

					if (j == 8) {
						try {
							Pictogram pictogram = pictograms.get(j);
							pictogramImage = new Jpeg(Base64.decodeBase64(pictogram.getImagem().split(",")[1]));
							addImage(pictogramImage, scale, scale, x + 32, y - 80, i, pdfreader, pdfStamper);
							addLabel(pictogram.getLabel(), pdfStamper.getOverContent(i), x + 52, y - 90);
						} catch (BadElementException | IOException e) {
							throw new BusinessException(e.getLocalizedMessage());
						}
					}
				}
			}
		}

		try

		{
			pdfStamper.close();
		} catch (DocumentException | IOException e) {
			throw new BusinessException(e.getLocalizedMessage());
		}
		pdfreader.close();

		byte[] manipulatePdfByteArray = fileOutputStream.toByteArray();

		return manipulatePdfByteArray;
	}

	public void addImage(Image image, float scaleX, float scaleY, float positionX, float positionY, int page,
			PdfReader reader, PdfStamper pdfStamper) throws BusinessException {

		PdfIndirectObject ref;
		PdfImage pdfImage;

		PdfContentByte over;

		try {
			image.scaleToFit(scaleX, scaleY);
			pdfImage = new PdfImage(image, "", null);
		} catch (BadPdfFormatException e) {
			throw new BusinessException(e.getLocalizedMessage());
		}

		try {
			ref = pdfStamper.getWriter().addToBody(pdfImage);
		} catch (IOException e) {
			throw new BusinessException(e.getLocalizedMessage());
		}

		image.setDirectReference(ref.getIndirectReference());
		image.setAbsolutePosition(positionX, positionY - 2);
		over = pdfStamper.getOverContent(page);
		over.saveState();
		over.setGState(new PdfGState());

		try {
			over.addImage(image);
		} catch (DocumentException e) {
			throw new BusinessException(e.getLocalizedMessage());
		}
	}

	public void addEmployeeData(String matricula, String idPeople, String nome, String cargo, PdfContentByte over,
			float x, float y, float z, float w) {

		Font font1 = new Font(FontFamily.UNDEFINED, (float) 9, Font.BOLD);
		Font font2 = new Font(FontFamily.UNDEFINED, (float) 8, Font.BOLD);
		Font font3 = new Font(FontFamily.UNDEFINED, (float) 6.);
		Phrase phrase1 = new Phrase("Mat: " + matricula + "  " + "ID: " + idPeople, font1);
		Phrase phrase2 = new Phrase(nome.toUpperCase(), font2);
		Phrase phrase3 = new Phrase(cargo.toUpperCase(), font3);

		ColumnText.showTextAligned(over, Element.ALIGN_CENTER, phrase1, x, y, 0);
		ColumnText.showTextAligned(over, Element.ALIGN_CENTER, phrase2, x, z, 0);
		ColumnText.showTextAligned(over, Element.ALIGN_CENTER, phrase3, x, w, 0);

	}

	public void addLabel(String label, PdfContentByte over, float x, float y) {
		Font font1 = new Font(FontFamily.UNDEFINED, (float) 8);
		Phrase phrase1 = new Phrase(label, font1);
		ColumnText.showTextAligned(over, Element.ALIGN_CENTER, phrase1, x, y, 0);

	}

	
	public byte[] pdfMerge(List<InputStream> files) throws BusinessException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfWriter writer = new PdfWriter(bos);
		PdfDocument destPdf = new PdfDocument(writer);
		PdfDocument srcPdf;

		for (InputStream inputStream : files) {

			try {
				srcPdf = new PdfDocument(new com.itextpdf.kernel.pdf.PdfReader(inputStream));
			} catch (Exception e) {
				throw new BusinessException(e.getLocalizedMessage());
			}

			for (int i = 1; i <= srcPdf.getNumberOfPages(); i++) {
				PdfPage origPage = srcPdf.getPage(i);
				PdfPage page = destPdf.addNewPage(new PageSize(origPage.getPageSize()));
				page.setRotation(origPage.getRotation());
				PdfCanvas canvas = new PdfCanvas(page);
				PdfFormXObject pageCopy;

				try {
					pageCopy = origPage.copyAsFormXObject(destPdf);
				} catch (IOException e) {
					throw new BusinessException(e.getLocalizedMessage());
				}

				canvas.addXObject(pageCopy, 0, 0);
			}
		}

		try {
			writer.close();
			destPdf.close();
		} catch (Exception e) {
			throw new BusinessException(e.getLocalizedMessage());
		}
		return bos.toByteArray();
	}

}
