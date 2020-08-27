package com.darkonnen.dojooverflow.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darkonnen.dojooverflow.models.Answer;
import com.darkonnen.dojooverflow.models.Question;
import com.darkonnen.dojooverflow.models.Tag;
import com.darkonnen.dojooverflow.repositories.AnswerRepository;
import com.darkonnen.dojooverflow.repositories.QuestionRepository;
import com.darkonnen.dojooverflow.repositories.TagRepository;

@Service
public class AppService {
	private AnswerRepository answerRepo;
	private QuestionRepository questionRepo;
	private TagRepository tagRepo;
	
	public AppService(AnswerRepository answerRepo, QuestionRepository questionRepo, TagRepository tagRepo) {
		this.answerRepo = answerRepo;
		this.questionRepo = questionRepo;
		this.tagRepo = tagRepo;
	}
	
	public List<Answer> allAnswers() {
		return answerRepo.findAll();
	}
	
	public List<Question> allQuestions() {
		return questionRepo.findAll();
	}
	
	public List<Tag> allTags() {
		return tagRepo.findAll();
	}
	
	public Answer findAnswer(Long id) {
		Optional<Answer> opAnswer = answerRepo.findById(id);
		if(opAnswer.isPresent()) {
			return opAnswer.get();
		} else {
			return null;
		}
	}
	
	public Question findQuestion(Long id) {
		Optional<Question> opQuestion = questionRepo.findById(id);
		if(opQuestion.isPresent()) {
			return opQuestion.get();
		} else {
			return null;
		}
	}
	
	public Tag findTag(Long id) {
		Optional<Tag> opTag = tagRepo.findById(id);
		if(opTag.isPresent()) {
			return opTag.get();
		} else {
			return null;
		}
	}
	
	public Answer saveAnswer(Answer a) {
		return answerRepo.save(a);
	}
	
	public Question saveQuestion(Question q) {
		return questionRepo.save(q);
	}
	
	public Tag saveTag(Tag t) {
		return tagRepo.save(t);
	}
	
	public Tag findTagBySub(String sub) {
		Optional<Tag> opTag = tagRepo.findBySubject(sub);
		if(opTag.isPresent()) {
			return opTag.get();
		} else {
			return null;
		}
	}
	
	public List<Tag> findTagBySubContaining(String sub) {
		return tagRepo.findBySubjectContaining(sub);
		
	}
	
	public List<String> processTags(String tags) {
		List<String> tagList = new ArrayList<String>(Arrays.asList(tags.split("\\s*,\\s*")));
		// separa strings por comas.
		return tagList;
	}
}