package com.devscompass.quarkustemplate.entity.note;

import com.devscompass.quarkustemplate.entity.common.AbstractTimestampedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notes")
public class Note extends AbstractTimestampedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  public String id;

  @Column(name = "title", nullable = false)
  public String title;

  @Column(name = "content", columnDefinition = "TEXT")
  public String content;
}
