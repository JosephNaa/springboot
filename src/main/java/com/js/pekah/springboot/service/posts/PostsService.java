package com.js.pekah.springboot.service.posts;

import com.js.pekah.springboot.domain.posts.Posts;
import com.js.pekah.springboot.domain.posts.PostsRepository;
import com.js.pekah.springboot.web.dto.PostsSaveRequestDto;
import com.js.pekah.springboot.web.dto.PostsUpdateRequestDto;
import com.js.pekah.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostsService{
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
