package com.darkonnen.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.darkonnen.dojooverflow.models.Answer;
import com.darkonnen.dojooverflow.models.Question;
import com.darkonnen.dojooverflow.models.QuestionTags;
import com.darkonnen.dojooverflow.models.Tag;
import com.darkonnen.dojooverflow.services.AppService;

@Controller
@RequestMapping("questions")
public class QuestionController {
	private AppService appService;
	
	public QuestionController(AppService appService) {
		this.appService = appService;
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("questions", appService.allQuestions());
		
		return "/questions/index.jsp";
	}
	
	@RequestMapping(value="new", method = RequestMethod.GET)
	public String newQuestion(@ModelAttribute("questionTags") QuestionTags questionTags) {
		return "/questions/new.jsp";
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, @ModelAttribute("answerData") Answer answer, Model model) {
		model.addAttribute("question", appService.findQuestion(id));
		return "/questions/show.jsp";		
	}
	
	@RequestMapping(value="new", method = RequestMethod.POST)
	public String addNewQuestion(@Valid @ModelAttribute("questionTags") QuestionTags questionTags, BindingResult result) {		
		if (result.hasErrors()) {
			return "questions/new.jsp";
		} else {
			if(!questionTags.getTags().equals("")) {
				List<String> tagScrub = appService.processTags(questionTags.getTags());			
				
				List<Tag> newTags = new ArrayList<Tag>();
				for (String tag : tagScrub) {
					if(!"".equals(tag)) {
						Tag newTag = new Tag(tag);
						if (appService.findTagBySub(tag) == null) {
							appService.saveTag(newTag);
							newTags.add(newTag);
						} else {
							newTags.add(appService.findTagBySub(tag));
						}
					}
				}
				appService.saveQuestion(new Question(questionTags.getQuestion(), newTags));
			} else {
				appService.saveQuestion(new Question(questionTags.getQuestion()));
			}

			return "redirect:/questions";			
		}
		
	}
	
}