package com.pdfservices.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.pdf.PdfReader;
import com.pdfservices.exception.BusinessException;
import com.pdfservices.model.EmployeeCard;
import com.pdfservices.services.EmployeeCardsService;

public class EmployeeCardsTest {

	public static void main(String[] args) throws BusinessException, IOException, BadElementException {

		String matricula = "0123";
		String idPeople = "12345678";
		String nome = "Maria da Silva";
		String cargo = "ASSISTENTE DE PCP I";
		String template = "templates/cracha.pdf";
		String foto = getImage();
		
		String cardPath = "C:\\dev\\temp\\cracha\\card_" + idPeople + ".pdf";
		EmployeeCard card = new EmployeeCard();
		
		card = new EmployeeCard(matricula, idPeople, nome, cargo, foto, card.getPictogramas());

		byte[] employeeCardBytes = new EmployeeCardsService().createEmployeeCard(card);

		try {
			FileUtils.writeByteArrayToFile(new File(cardPath), employeeCardBytes);
		} catch (IOException e) {
			throw new BusinessException(e.getLocalizedMessage());
		}

	}

	private static String getImage() {
		return "data:image/jpeg;base64,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";
	}

	private static String getPictogram() {
		return "data:image/jpeg;base64,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";
	}

}