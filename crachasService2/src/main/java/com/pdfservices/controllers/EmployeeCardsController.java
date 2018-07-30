package com.pdfservices.controllers;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.pdfservices.exception.BusinessException;
import com.pdfservices.model.EmployeeCard;
import com.pdfservices.services.EmployeeCardsService;

@RestController
@RequestMapping("card")
public class EmployeeCardsController {

	@RequestMapping(value = "download", method = RequestMethod.POST)
	public HttpEntity<byte[]> downloadEmployeeCard(@RequestBody List<EmployeeCard> employeeCards)
			throws BusinessException {

		EmployeeCardsService cardsService = new EmployeeCardsService();
		byte[] cardBytes = null;
		byte[] employeeCardsBytes = null;
		List<InputStream> cardsIs = new ArrayList<>();
		HttpEntity<byte[]> employeeCardsEntity = null;
		HttpHeaders header = new HttpHeaders();
		header.set(HttpHeaders.CONTENT_TYPE, "application/pdf");
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "\"" + "crachas.pdf" + "\"");

		try {

			for (EmployeeCard employeeCard : employeeCards) {
				cardBytes = cardsService.createEmployeeCard(employeeCard);
				cardsIs.add(new ByteArrayInputStream(cardBytes));
			}

			employeeCardsBytes = cardsService.pdfMerge(cardsIs);
			employeeCardsEntity = new HttpEntity<byte[]>(employeeCardsBytes, header);
		} catch (HttpServerErrorException e) {
			throw new BusinessException(e.getLocalizedMessage());
		}

		return employeeCardsEntity;
	}
}
