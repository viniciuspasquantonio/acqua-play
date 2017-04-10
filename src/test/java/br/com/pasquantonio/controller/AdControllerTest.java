package br.com.pasquantonio.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import br.com.pasquantonio.entity.Ad;
import br.com.pasquantonio.repository.AdRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdControllerTest {

	private static final String ID_FIELD = "id";
	private static final String FIRSTAD_TITLE = "first ad";
	private static final String SECOND_AD_TITLE = "second ad";
	private static final String THIRD_AD_TITLE = "third ad";
	private static final String FIRSTAD_DESCRIPTION = "first ad description";
	private static final String SECOND_AD_DESCRIPTION = "second ad description";
	private static final String THIRD_AD_DESCRIPTION = "thirdad description";
	private static final String TITLE_FIELD = "title";
	private static final String DESCRIPTION_FIELD = "description";

	private static final String ADS_RESOURCE = "/ads/";
	private static final String ADS_RESOURCE_ID = "/ads/{id}";

	@LocalServerPort
	private int port;

	@Autowired
	private AdRepository repository;

	private Ad firstAd;
	private Ad secondAd;
	private Ad thirdAd;

	
	@Before
	public void setUp() {
		repository.deleteAll();
		
		firstAd = new Ad();
		firstAd.setTitle(FIRSTAD_TITLE);
		firstAd.setDescription(FIRSTAD_DESCRIPTION);
		firstAd.setPrice(new BigDecimal(100d));

		firstAd = repository.save(firstAd);
		
		secondAd = new Ad();
		secondAd.setTitle(SECOND_AD_TITLE);
		secondAd.setDescription(SECOND_AD_DESCRIPTION);
		secondAd.setPrice(new BigDecimal(200d));

		secondAd = repository.save(secondAd);
		
		thirdAd = new Ad();
		thirdAd.setTitle(THIRD_AD_TITLE);
		thirdAd.setDescription(THIRD_AD_DESCRIPTION);
		thirdAd.setPrice(new BigDecimal(300d));
		
		RestAssured.port = port;
		
	}

	@Test
	public void getAdsShouldReturnBothAds() {
		given()
			.contentType("application/json")
		.when()
			.get(ADS_RESOURCE)
		.then()
			.statusCode(HttpStatus.SC_OK)
			.body(DESCRIPTION_FIELD, hasItems(FIRSTAD_DESCRIPTION, SECOND_AD_DESCRIPTION))
			.body(TITLE_FIELD, hasItems(FIRSTAD_TITLE, SECOND_AD_TITLE));
	}
	
	@Test
    public void canFetchFirstAd() {
		given()
			.contentType(ContentType.JSON)
        .when()
        	.get(ADS_RESOURCE_ID, firstAd.getId())
        .then()
        	.statusCode(HttpStatus.SC_OK)
        	.body(DESCRIPTION_FIELD, is(FIRSTAD_DESCRIPTION))
    	    .body(TITLE_FIELD, is(FIRSTAD_TITLE))
            .body(ID_FIELD, is(firstAd.getId()));
    }
	
	@Test
	public void addAdShouldReturnSavedAd() {
	  given()
	    .body(thirdAd)
	    .contentType(ContentType.JSON)
	  .when()
	    .post(ADS_RESOURCE)
	  .then()
	    .statusCode(HttpStatus.SC_CREATED)
	    .body(DESCRIPTION_FIELD, is(THIRD_AD_DESCRIPTION))
	    .body(TITLE_FIELD, is(THIRD_AD_TITLE));
	}
	
	@Test
	public void addAdShouldReturnBadRequestWithoutBody() {
		given()
	    	.contentType(ContentType.JSON)
		.when()
	    	.post(ADS_RESOURCE)
	    .then()
	    	.statusCode(HttpStatus.SC_BAD_REQUEST);
	}
	
	@Test
	public void addAdShouldReturnNotSupportedMediaTypeIfNonJSON() {
	  given()
	    .body(thirdAd)
	  .when()
	    .post(ADS_RESOURCE)
	  .then()
	    .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
	}
	
	@Test
	public void updateAdShouldReturnUpdatedAd() {
	  // Given an unchecked first item
	  Ad ad = new Ad();
	  ad.setDescription(FIRSTAD_DESCRIPTION);
	  ad.setTitle(FIRSTAD_TITLE);
	  given()
	    .body(ad)
	    .contentType(ContentType.JSON)
	  .when()
	    .put(ADS_RESOURCE_ID, firstAd.getId())
	  .then()
	    .statusCode(HttpStatus.SC_OK)
	    .body(DESCRIPTION_FIELD, is(FIRSTAD_DESCRIPTION))
	    .body(TITLE_FIELD, is(FIRSTAD_TITLE));
	}
	  
	@Test
	public void updateAdShouldReturnBadRequestWithoutBody() {
		given()
	  		.contentType(ContentType.JSON)
		.when()
	    	.put(ADS_RESOURCE_ID, firstAd.getId())
    	.then()
    		.statusCode(HttpStatus.SC_BAD_REQUEST);
	}
	  
	@Test
	public void updateAdShouldReturnNotSupportedMediaTypeIfNonJSON() {
		given()
	    	.body(firstAd)
    	.when()
	    	.put(ADS_RESOURCE_ID, firstAd.getId())
    	.then()
	    	.statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
	}
	 
	@Test
	public void deleteItemShouldReturnNoContent() {
		given()
  			.contentType(ContentType.JSON)
		.when()
	    	.delete(ADS_RESOURCE_ID, secondAd.getId())
    	.then()
	    	.statusCode(HttpStatus.SC_NO_CONTENT);
	}


}
