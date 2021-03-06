
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.NutritionistService;
import domain.Article;
import domain.Nutritionist;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends AbstractController {

	// Services

	@Autowired
	private ArticleService		articleService;

	@Autowired
	private NutritionistService	nutritionistService;


	// Constructor

	public ArticleController() {
		super();
	}

	// List
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int nutritionistId) {
		ModelAndView result;
		Collection<Article> articles;
		Nutritionist principal;
		Nutritionist owner;

		try {
			result = new ModelAndView("article/list");
			articles = this.articleService.findArticlesByNutritionist(nutritionistId);
			owner = this.nutritionistService.findOne(nutritionistId);

			try {
				principal = this.nutritionistService.findByPrincipal();
			} catch (final Exception e1) {
				principal = null;
			}

			result.addObject("principal", principal);
			result.addObject("owner", owner);
			result.addObject("articles", articles);
			result.addObject("requestURI", "article/list.do");

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../error.do");
		}

		return result;
	}

	@RequestMapping(value = "/allArticlesList", method = RequestMethod.GET)
	public ModelAndView allArticlesList() {
		ModelAndView result;
		Collection<Article> articles;
		Nutritionist principal;

		try {
			result = new ModelAndView("article/list");

			articles = this.articleService.findAll();
			try {
				principal = this.nutritionistService.findByPrincipal();
			} catch (final Exception e1) {
				principal = null;
			}
			if (principal != null)
				result.addObject("principal", principal);

			result.addObject("owner", null);
			result.addObject("articles", articles);
			result.addObject("requestURI", "article/allArticlesList.do");

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../error.do");
		}

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int articleId) {
		ModelAndView result;
		final Article article;

		try {
			result = new ModelAndView("article/display");
			article = this.articleService.findOne(articleId);

			result.addObject("article", article);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../error.do");
		}

		return result;
	}

}
