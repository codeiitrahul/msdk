/*
 * (C) Copyright 2015-2017 by MSDK Development Team
 *
 * This software is dual-licensed under either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1 as published by the Free
 * Software Foundation
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by the Eclipse Foundation.
 */

package io.github.msdk.datamodel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.collect.Range;

/**
 * Implementation of IsolationInfo
 */
public class SimpleIsolationInfo implements IsolationInfo {

  private @Nonnull Range<Double> isolationMzRange;
  private @Nullable Float ionInjectTime;
  private @Nullable Double precursorMz;
  private @Nullable Integer precursorCharge;
  private @Nullable ActivationInfo activationInfo;

  /**
   * <p>Constructor for SimpleIsolationInfo.</p>
   *
   * @param isolationMzRange a {@link com.google.common.collect.Range} object.
   */
  public SimpleIsolationInfo(@Nonnull Range<Double> isolationMzRange) {
    Preconditions.checkNotNull(isolationMzRange);
    this.isolationMzRange = isolationMzRange;
    ionInjectTime = null;
    precursorMz = null;
    precursorCharge = null;
    activationInfo = null;
  }

  /**
   * <p>Constructor for SimpleIsolationInfo.</p>
   *
   * @param isolationMzRange a {@link com.google.common.collect.Range} object.
   * @param ionInjectTime a {@link java.lang.Float} object.
   * @param precursorMz a {@link java.lang.Double} object.
   * @param precursorCharge a {@link java.lang.Integer} object.
   * @param activationInfo a {@link io.github.msdk.datamodel.ActivationInfo} object.
   */
  public SimpleIsolationInfo(@Nonnull Range<Double> isolationMzRange, @Nullable Float ionInjectTime,
      @Nullable Double precursorMz, @Nullable Integer precursorCharge,
      @Nullable ActivationInfo activationInfo) {
    Preconditions.checkNotNull(isolationMzRange);
    this.isolationMzRange = isolationMzRange;
    this.ionInjectTime = ionInjectTime;
    this.precursorMz = precursorMz;
    this.precursorCharge = precursorCharge;
    this.activationInfo = activationInfo;
  }

  /** {@inheritDoc} */
  @Override
  @Nonnull
  public Range<Double> getIsolationMzRange() {
    return isolationMzRange;
  }

  /** {@inheritDoc} */
  @Override
  @Nullable
  public Float getIonInjectTime() {
    return ionInjectTime;
  }

  /** {@inheritDoc} */
  @Override
  @Nullable
  public Double getPrecursorMz() {
    return precursorMz;
  }

  /** {@inheritDoc} */
  @Override
  @Nullable
  public Integer getPrecursorCharge() {
    return precursorCharge;
  }

  /** {@inheritDoc} */
  @Override
  @Nullable
  public ActivationInfo getActivationInfo() {
    return activationInfo;
  }

}
