package com.darkonnen.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.darkonnen.dojooverflow.models.Question;
import com.darkonnen.dojooverflow.models.Tag;
import com.darkonnen.dojooverflow.services.AppService;


@Controller
@RequestMapping("tags")
public class TagController {
	private AppService appService;

	public TagController(AppService appService) {
		this.appService = appService;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		Tag tag = appService.findTag(id);
		
		model.addAttribute("subject", tag.getSubject());
		model.addAttribute("questions", tag.getQuestions());
		return "/tags/show.jsp";
	}
	
	@RequestMapping(value="q", method = RequestMethod.GET)
	public String search(@RequestParam(value="subject") String subject, Model model) {
		List<Tag> tags = appService.findTagBySubContaining(subject);
		
		List<Question> questions = new ArrayList<Question>(); 
		for (Tag tag : tags) {
			questions.addAll(tag.getQuestions());
		}
		
		model.addAttribute("subject", subject);
		model.addAttribute("questions", questions);
		return "/tags/show.jsp";
	}
}